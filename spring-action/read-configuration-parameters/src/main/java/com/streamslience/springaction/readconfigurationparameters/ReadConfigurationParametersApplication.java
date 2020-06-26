package com.streamslience.springaction.readconfigurationparameters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * SpringBoot 配置文件参数读取并生成bean对象
 */
@SpringBootApplication
@EnableConfigurationProperties({WithConfigurationProperties.class})
public class ReadConfigurationParametersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadConfigurationParametersApplication.class, args);
    }

}
