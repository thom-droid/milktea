package com.millktea.api.config.domain.business;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"com.millktea.api.domain.business", "com.millktea.core.domain.business"})
public class BusinessTestConfig {
}
