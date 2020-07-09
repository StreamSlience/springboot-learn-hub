package com.streamslience.springdatamongodb.mongodbdynamicdatasource.first.configuration;

import com.mongodb.MongoClientURI;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author StreamSlience
 * @date 2020-07-08 23:33
 */
//@Configuration
//@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongodb.mongodbdynamicdatasource.first.repository.primary",mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {

//    @Bean(name = "primaryMongoTemplate")
//    @Primary
//    public MongoTemplate primaryMongoTemplate(){
//        return new MongoTemplate(primaryFactory(primaryMongoProperties()));
//    }
//
//    @Bean
//    @Primary
//    public MongoDbFactory primaryFactory(MongoProperties mongoProperties) {
//        return new SimpleMongoDbFactory(new MongoClientURI(primaryMongoProperties().getUri()));
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix="spring.data.mongodb.primary")
//    public MongoProperties primaryMongoProperties() {
//        return new MongoProperties();
//    }

}