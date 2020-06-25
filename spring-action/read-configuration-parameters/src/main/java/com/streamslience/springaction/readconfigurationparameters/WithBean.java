package com.streamslience.springaction.readconfigurationparameters;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * 使用{@code @ConfigurationProperties} 和 {@code @Bean} 读取配置文件参数
 *
 * @author StreamSlience
 * @date 2020-06-25 23:52
 */
@Configuration
@PropertySource(value = "classpath:application.yml")
public class WithBean {

    @Bean
    @ConfigurationProperties("car")
    public Car getCarBean() {
        return new Car();
    }

    @Bean
    @ConfigurationProperties("plane")
    public Plane getPlaneBean() {
        return new Plane();
    }

    @Data
    static class Car {

        private String name;

        private String weight;

        private Long price;

        private String id;

        private List<String> rank;

    }

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    static class Plane extends Car {

    }
}
