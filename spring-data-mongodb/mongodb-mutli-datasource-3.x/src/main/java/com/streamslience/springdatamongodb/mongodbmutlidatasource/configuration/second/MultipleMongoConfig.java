package com.streamslience.springdatamongodb.mongodbmutlidatasource.configuration.second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * @author StreamSlience
 * @date 2020-07-21 10:38
 */
@Configuration
public class MultipleMongoConfig {

    @Autowired
    private MultipleMongoProperties multipleMongoProperties;

    @Primary
    @Bean(name = "primaryMongoTemplate_2")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory(multipleMongoProperties.getPrimary()));
    }

    @Bean(name = "secondaryMongoTemplate_2")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory(multipleMongoProperties.getSecondary()));
    }

    private MongoDatabaseFactory mongoDatabaseFactory(MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

}
