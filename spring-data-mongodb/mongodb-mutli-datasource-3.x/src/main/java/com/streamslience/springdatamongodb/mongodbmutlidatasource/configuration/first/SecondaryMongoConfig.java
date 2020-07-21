package com.streamslience.springdatamongodb.mongodbmutlidatasource.configuration.first;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author StreamSlience
 * @date 2020-07-09 13:11
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongodb.mongodbmutlidatasource.repositories.secondary",
        mongoTemplateRef = "secondaryMongoTemplate_1")
public class SecondaryMongoConfig {

    @Bean(name = "secondaryMongoTemplate_1")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryFactory(secondaryMongoProperties()));
    }

    @Bean
    public MongoDatabaseFactory secondaryFactory(MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(secondaryMongoProperties().getUri());
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
    public MongoProperties secondaryMongoProperties() {
        return new MongoProperties();
    }
}
