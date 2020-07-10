package com.streamslience.springdatamongo.mongodbdatadatasource.configuration;


import com.mongodb.MongoClient;
import lombok.Data;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;


/**
 * @author StreamSlience
 * @date 2020-07-09 23:46
 */
@Data
public abstract class AbstractMongoConfig {

    private String host;
    private String database;
    private int port;

    MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient(host, port), database);
    }

    abstract public MongoTemplate getMongoTemplate();

}
