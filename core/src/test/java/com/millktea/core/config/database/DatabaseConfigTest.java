package com.millktea.core.config.database;

import com.millktea.core.config.database.config.BusinessTestConfig;
import com.millktea.core.config.database.config.DatabaseTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes =  {DatabaseTestConfiguration.class, BusinessTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DatabaseConfigTest {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    DataSource dataSource;

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
            System.out.println("dataSource = " + dataSource.getConnection().getMetaData().getDriverName());
        } catch (Exception e) {
            fail("Database connection failed");
        }
    }

}