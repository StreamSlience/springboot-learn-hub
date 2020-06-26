package com.streamslience.springaction.readconfigurationparameters;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * 使用{@code @ConfigurationProperties} 和 {@code @Configuration}/{@code @Component}同时作用于类读取配置文件参数并注册为bean对象。
 *
 * 当然还有另外一种形式，可以不使用{@code @Configuration}注入bean对象,
 * 而是在启动类中使用{@code @EnableConfigurationProperties}来指定要生成的bean对象
 *
 * @author StreamSlience
 * @date 2020-06-25 14:35
 */
@Data
//@Configuration
@ConfigurationProperties(prefix = "library")
public class WithConfigurationProperties {

    /**
     * 基本类型、String类型 变量
     */
    private String name;

    /**
     * 引用变量
     */
    private Info info;

    /**
     * 元素为基本类型或String类型的集合
     */
    private List<String> area;

    /**
     * 元素为引用类型的集合
     */
    private List<Book> books;

    /**
     * 基本类型或String的Map
     */
    private Map<String,String> borrow;

    /**
     * 引用类型的Map
     */
    private Map<String,Magazine> magazine;

    @Data
    static class Info{
        private String address;
    }

    @Data
    static class Book {
        private String name;
        private String description;
    }

    @Data
    static class Magazine{
        private String content;
        private Integer price;
    }

}
