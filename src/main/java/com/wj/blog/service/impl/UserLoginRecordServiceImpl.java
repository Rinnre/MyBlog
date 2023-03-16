package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.UserLoginRecordMapper;
import com.wj.blog.pojo.entity.UserLoginRecord;
import com.wj.blog.service.UserLoginRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
