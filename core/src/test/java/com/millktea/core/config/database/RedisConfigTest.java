package com.millktea.core.config.database;

import com.millktea.core.config.database.config.RedisTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {DatabaseConfig.class, RedisTestConfiguration.class})
public class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("test", "test");
        assertNotNull(opsForValue.get("test"));
    }
}
