package com.streamslience.springdatamongo.mongodbdatadatasource.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author StreamSlience
 * @date 2020-07-09 23:48
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.streamslience.springdatamongo.mongodbdatadatasource.repositories.primary",mongoTemplateRef = "primaryMongoTemplate")
@ConfigurationProperties(prefix = "spring.data.mongodb.primary")
public class PrimaryMongoConfig extends AbstractMongoConfig {
    @Override
    @Primary
    @Bean("primaryMongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
