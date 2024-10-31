package com.millktea.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.millktea.api", "com.millktea.core"})
public class MilkteaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MilkteaApplication.class, args);
    }

}
