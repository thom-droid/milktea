package com.millktea.core.config.database;

import com.millktea.core.config.database.config.DatabaseTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes = {DatabaseConfig.class, DatabaseTestConfiguration.class})
class DatabaseConfigTest {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        assertNotNull(ctx);
    }

    @Test
    void dataSourceLoads() {
        assertNotNull(dataSource);
    }

    @Test
    void databaseConnection() {
        try {
            dataSource.getConnection();
        } catch (Exception e) {
            fail("Database connection failed");
        }
    }

}