package com.streamslience.springaction.logconfigurationlogback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot 整合日志框架Logback
 *
 * TODO:1.实现mybait日志打印sql
 *
 */
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.streamslience.springaction.logconfigurationlogback.dao")
public class LogConfigurationLogbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogConfigurationLogbackApplication.class, args);
    }

}
