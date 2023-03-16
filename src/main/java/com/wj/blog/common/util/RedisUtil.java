package com.wj.blog.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author wj
 * @date 2023/03/08
 */
@Component
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class RedisUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplateForObject;


    /**
     * 存入redis
     *
     * @param key   键
     * @param value 值
     */
    public void setKeyValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存入redis带过期时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 到期时间
     * @param timeUnit   时间单位
     */
    public void setKeyValue(String key, String value, Long expireTime, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }


    /**
     * 获取key对应的值
     *
     * @param key 键
     * @return {@link String} 值
     */
    public String getValueByKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除redis键值对
     *
     * @param key 键
     * @return boolean 操作结果
     */
    public boolean removeKeyValue(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.delete(key));
    }

    /**
     * 存入键-对象
     *
     * @param key   键
     * @param value 对象
     */
    public void setKeyObject(String key, Object value) {
        redisTemplateForObject.opsForValue().set(key, value);
    }

    /**
     * 批量存入键-对象
     *
     * @param keyMap 键-对象映射map
     */
    public void mSetKeyObject(Map<String, Object> keyMap) {
        redisTemplateForObject.opsForValue().multiSet(keyMap);
    }

    /**
     * 从redis中批量取出对象
     *
     * @param keys 键
     * @return {@link List}<{@link Object}> 对象集合
     */
    public List<Object> mGetObjectByKey(List<String> keys) {
        return redisTemplateForObject.opsForValue().multiGet(keys);
    }

    /**
     * 从redis中取出对象
     *
     * @param key 键
     * @return {@link Object} 对象
     */
    public Object getObjectByKey(String key) {
        return redisTemplateForObject.opsForValue().get(key);
    }
}
