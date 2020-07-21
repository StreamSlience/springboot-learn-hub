package com.streamslience.springdatamongodb.mongodbmutlidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * Spring data mongodb 多数据源配置项目
 * 使用 3.X 版本 spring-data-mongodb
 */
@SpringBootApplication
public class MongodbMutliDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbMutliDatasourceApplication.class, args);
    }

}
