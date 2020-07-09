package com.streamslience.springdatamongodb.mongodbdynamicdatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongodb.mongodbdynamicdatasource")
@SpringBootApplication
public class MongodbDynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbDynamicDatasourceApplication.class, args);
    }

}
