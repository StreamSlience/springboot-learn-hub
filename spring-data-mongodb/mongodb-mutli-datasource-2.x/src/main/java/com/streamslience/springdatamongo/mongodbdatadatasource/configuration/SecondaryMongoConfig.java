package com.streamslience.springdatamongo.mongodbdatadatasource.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author StreamSlience
 * @date 2020-07-09 23:51
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongo.mongodbdatadatasource.repositories.secondary", mongoTemplateRef = "secondaryMongoTemplate2")
@ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
public class SecondaryMongoConfig extends AbstractMongoConfig {
    @Override
    @Bean(name = "secondaryMongoTemplate2")
    public MongoTemplate getMongoTemplate(){
        return new MongoTemplate(mongoDbFactory());
    }
}
