package com.redis.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisAccess {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void check() {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.increment("myKey:", 1);
    }

}
