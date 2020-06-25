package com.streamslience.springaction.readconfigurationparameters;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * 使用{@code @ConfigurationProperties} 和 {@code @Bean} 读取配置文件参数
 *
 * @author StreamSlience
 * @date 2020-06-25 23:52
 */
@Configuration
@PropertySource(value = "classpath:bean.properties", encoding = "utf-8")
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

        private Map<String,Integer> food;

        private Person driver;

        private Person[] passenger;

        private Map<String, Person> staff;

        @Data
        static class Person {
            private String name;
            private Integer age;
            private Integer weight;
        }
    }
}
