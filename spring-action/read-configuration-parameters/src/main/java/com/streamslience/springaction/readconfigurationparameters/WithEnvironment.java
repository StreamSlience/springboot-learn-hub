package com.streamslience.springaction.readconfigurationparameters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * 通过Environment对象获取配置文件参数
 *
 * @author StreamSlience
 * @date 2020-06-25 14:22
 */
@Configuration
public class WithEnvironment {

    private final Environment environment;

    @Autowired
    public WithEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Properties bookProperties() {
        Properties properties = new Properties();
        properties.put("book.name.en", environment.getRequiredProperty("book.name.en"));
        properties.put("book.name.zh", environment.getRequiredProperty("book.name.zh"));
        properties.put("book.author.name", environment.getRequiredProperty("book.author.name"));
        properties.put("book.author.nationality", environment.getRequiredProperty("book.author.nationality"));
        properties.put("book.author.born", environment.getRequiredProperty("book.author.born"));
        properties.put("book.author.died", environment.getRequiredProperty("book.author.died"));
        //如果配置文件中不存在对应的路径将抛出异常
        if (StringUtils.isNotEmpty(environment.getRequiredProperty("book.press"))) {
            properties.put("book.press", environment.getRequiredProperty("book.press"));
        }
        return properties;
    }
}
