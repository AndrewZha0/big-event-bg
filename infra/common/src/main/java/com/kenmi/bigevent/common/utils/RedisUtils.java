package com.kenmi.bigevent.common.utils;

import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @description: Redis处理工具类
 * @author: andrew
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写入缓存
     */
    public void set(final String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 写入缓存 并设置过期时间
     */
    public void setToken(String key, Object value, Long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 读取缓存
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 写入缓存 并设置过期时间
     */
    public void set(final String key, Object value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * 删除缓存
     */
    public void delete(final String key) {
        redisTemplate.delete(key);
    }

    /**
     * 分布式锁 加锁
     */
    public boolean getLock(String key, String value, long timeout) {
        Boolean lockStatus = redisTemplate.opsForValue().setIfAbsent(key, value,
                Duration.ofSeconds(timeout));
        return Boolean.TRUE.equals(lockStatus);
    }

    /**
     * 分布式锁 释放锁
     */
    public Long releaseLock(String key, String value) {
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        return redisTemplate.execute(redisScript, Collections.singletonList(key), value);
    }

    public Object getIncr(final String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public Object incr(String key, long delta) {
        if (delta < 0L) {
            ParamUtils.fail(ErrorCodeEnum.PARAM_ILLEGAL, "递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


}
