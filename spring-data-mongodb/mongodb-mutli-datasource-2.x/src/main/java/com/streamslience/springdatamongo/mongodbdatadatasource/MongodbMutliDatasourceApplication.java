package com.streamslience.springdatamongo.mongodbdatadatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Spring data mongodb 多数据源配置项目
 * 使用 2.X 版本 spring-data-mongodb
 *
 * 需要注意spring springboot 和 spring data mongodb 版本之间的是否兼容,
 * 一般由于版本引起的问题如下:
 * 1.Caused by: java.lang.ClassNotFoundException: org.springframework.data.mongodb.MongoDatabaseFactory
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MongodbMutliDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbMutliDatasourceApplication.class, args);
    }

}
