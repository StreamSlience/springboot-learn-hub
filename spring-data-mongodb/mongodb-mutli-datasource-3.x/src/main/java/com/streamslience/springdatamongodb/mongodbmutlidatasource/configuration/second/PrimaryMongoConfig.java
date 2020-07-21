package com.streamslience.springdatamongodb.mongodbmutlidatasource.configuration.second;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author StreamSlience
 * @date 2020-07-21 10:58
 */
@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongodb.mongodbmutlidatasource.repositories.secondary",
        mongoTemplateRef = "primaryMongoTemplate_2")
public class PrimaryMongoConfig {
}
