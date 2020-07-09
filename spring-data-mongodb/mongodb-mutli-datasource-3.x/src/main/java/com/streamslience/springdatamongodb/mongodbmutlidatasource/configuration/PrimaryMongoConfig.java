package com.streamslience.springdatamongodb.mongodbmutlidatasource.configuration;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author StreamSlience
 * @date 2020-07-09 13:06
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongodb.mongodbmutlidatasource.repositories.primary",
        mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryFactory(primaryMongoProperties()));
    }

    @Bean
    @Primary
    public MongoDatabaseFactory primaryFactory(MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(primaryMongoProperties().getUri());
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.data.mongodb.primary")
    public MongoProperties primaryMongoProperties() {
        return new MongoProperties();
    }
}
