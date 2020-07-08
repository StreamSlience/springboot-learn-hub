package com.streamslience.springdatamongodb.mongodbdynamicdatasource.first.configuration;

import com.mongodb.MongoClientURI;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author StreamSlience
 * @date 2020-07-08 23:37
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongodb.mongodbdynamicdatasource.first.repository.secondary",mongoTemplateRef = "secondaryMongoTemplate")
public class SecondaryMongoConfig {


    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryFactory(secondaryMongoProperties()));
    }

    @Bean
    public MongoDbFactory secondaryFactory(MongoProperties mongoProperties) {
        return new SimpleMongoDbFactory(new MongoClientURI(secondaryMongoProperties().getUri()));
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
    public MongoProperties secondaryMongoProperties(){
        return new MongoProperties();
    }

}
