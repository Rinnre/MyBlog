package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.aop.annation.NumberCount;
import com.wj.blog.common.enums.RedisOperationEnum;
import com.wj.blog.common.enums.StatisticsEnum;
import com.wj.blog.common.thread.AsyncManager;
import com.wj.blog.common.thread.TaskFactory;
import com.wj.blog.mapper.ArticleMapper;
import com.wj.blog.mapper.StatisticsMapper;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.dto.ArticleQueryParam;
import com.wj.blog.pojo.entity.Article;
import com.wj.blog.pojo.entity.Category;
import com.wj.blog.pojo.entity.Statistics;
import com.wj.blog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private StatisticsMapper statisticsMapper;

    private final String REDIS_HEAD = "statistics";
    @Resource
    private TaskFactory taskFactory;

    @NumberCount(mode = "list")
    @Override
    public List<ArticleDto> searchArticleList(ArticleQueryParam articleQueryParam) {

        if (null != articleQueryParam) {
            Integer startPageNumber = null;
            if (null != articleQueryParam.getPage() && null != articleQueryParam.getSize()) {
                startPageNumber = (articleQueryParam.getPage() - 1) * articleQueryParam.getSize();
            }
            articleQueryParam.setPage(startPageNumber);
        }
        return baseMapper.selectArticleList(articleQueryParam);
    }

    @NumberCount(mode = "addView")
    @Override
    public ArticleDto searchArticleDetail(String id) {
        //  访问量增加
        return baseMapper.searchArticleDetail(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createArticle(ArticleDto articleDto) {
        // 文章信息保存
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        baseMapper.insert(article);
        // TODO 定时发布文章需创建定时任务

        // 文章tag保存
        List<Category> tags = articleDto.getTags();
        if (tags != null && !tags.isEmpty()) {
            baseMapper.insertTags(tags, article.getId());
        }
        // 初始化文章附属信息
        Statistics statistics = new Statistics();
        statistics.setSourceType(StatisticsEnum.ARTICLE.getValue());
        statistics.setSourceId(article.getId());
        // 异步存入redis
        AsyncManager.me().execute(taskFactory.redisOperation(REDIS_HEAD + article.getId(), statistics, RedisOperationEnum.INSERT_UPDATE.getValue()));
        statisticsMapper.insert(statistics);
    }

    @Override
    public void removeArticle(String uid, String id) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getId, id).eq(Article::getUserId, uid);
        Article article = baseMapper.selectOne(articleLambdaQueryWrapper);
        if (null != article) {
            baseMapper.deleteById(id);
            AsyncManager.me().execute(taskFactory.redisOperation(REDIS_HEAD + id, null, RedisOperationEnum.DELETE.getValue()));
        }
    }
}
