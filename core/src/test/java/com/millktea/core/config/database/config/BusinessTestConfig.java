package com.millktea.core.config.database.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.millktea.core.domain.business"})
public class BusinessTestConfig {
}
