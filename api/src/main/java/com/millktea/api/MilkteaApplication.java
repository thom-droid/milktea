package com.millktea.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.millktea.api", "com.millktea.core"})
@EnableJpaRepositories(basePackages = {"com.millktea.core.domain"})
@EntityScan(basePackages = {"com.millktea.core.domain"})
public class MilkteaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MilkteaApplication.class, args);
    }

}
