package com.millktea.api.config.domain.business;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"com.millktea.api.domain.user", "com.millktea.core.domain.user"})
public class UserTestConfig {
}
