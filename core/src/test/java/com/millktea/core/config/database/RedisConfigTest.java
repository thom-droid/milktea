package com.millktea.core.config.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
        assertNotNull(redisTemplate);
    }

    @Test
    void redisConnection() {
        assertDoesNotThrow(() -> redisTemplate.getConnectionFactory().getConnection());
    }

    @Test
    void redisTemplate() {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("test", "testValue");
        assertNotNull(opsForValue.get("test"));
    }
}
