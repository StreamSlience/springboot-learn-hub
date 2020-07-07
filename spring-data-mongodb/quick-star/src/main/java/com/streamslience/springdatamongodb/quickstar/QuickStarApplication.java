package com.streamslience.springdatamongodb.quickstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongodb.quickstar")
@SpringBootApplication
public class QuickStarApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickStarApplication.class, args);
    }

}
