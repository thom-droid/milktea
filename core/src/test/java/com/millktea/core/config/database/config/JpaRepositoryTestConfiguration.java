package com.millktea.core.config.database.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories(basePackages = "com.millktea.core.domain")
@EntityScan(basePackages = "com.millktea.core.domain")
public class JpaRepositoryTestConfiguration {
}
