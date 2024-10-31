package com.millktea.core.config.database.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@TestConfiguration
public class DatabaseTestConfiguration {
    @Bean
    @Primary // This ensures this DataSource is used when there's a need for a DataSource bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/milktea?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("milktea");
        dataSource.setPassword("1111");
        return dataSource;
    }

}
