package com.streamslience.springdatamongodb.mongodbmutlidatasource.configuration.second;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author StreamSlience
 * @date 2020-07-21 10:33
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MultipleMongoProperties {

    private MongoProperties primary;

    private MongoProperties secondary;

}
