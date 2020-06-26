package com.streamslience.springaction.multienvironmentconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 多环境下设置配置文件
 * <p>
 * 在现实的开发环境中，我们需要不同的配置环境；格式为application-{profile}.properties，其中{profile}对应你的环境标识。
 */
@SpringBootApplication
public class MultiEnvironmentConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiEnvironmentConfigurationApplication.class, args);
    }

}
