package com.streamslience.springaction.readconfigurationparameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Properties;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReadConfigurationParametersApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private WithValue withValue;
    @Test
    public void value() {
        System.out.println(withValue);
    }

    @Autowired
    private WithEnvironment withEnvironment;
    @Test
    public void environment() {
        Properties properties = withEnvironment.bookProperties();
        properties.forEach((o, o2) -> System.out.println(o + ":" + o2));
    }

    @Autowired
    private WithConfigurationProperties withConfigurationProperties;
    @Test
    public void configurationProperties() {
        System.out.println(withConfigurationProperties.getName());

        System.out.println(withConfigurationProperties.getInfo());

        withConfigurationProperties.getArea().forEach(System.out::println);

        withConfigurationProperties.getBooks().forEach(System.out::println);

        withConfigurationProperties.getBorrow().forEach((s, s2) -> System.out.println(s + ":" + s2));

        withConfigurationProperties.getMagazine().forEach((s, magazine) -> System.out.println(s + ":" + magazine));
    }

    @Autowired
    private WithBean withBean;
    @Test
    public void bean(){
        WithBean.Car car = withBean.getCarBean();
        System.out.println(car);

        WithBean.Plane plane = withBean.getPlaneBean();
        System.out.println(plane);
    }
}
