package com.streamslience.springaction.logconfigurationlogback;

import com.streamslience.springaction.logconfigurationlogback.controller.MyabtisController;
import com.streamslience.springaction.logconfigurationlogback.entity.MyabtisEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogConfigurationLogbackApplicationTests {

    @Autowired
    private MyabtisController myabtisController;

    @Test
    public void contextLoads() {
    }

    @Sql(scripts = "classpath:mybaits.sql")
    @Test
    public void selectById() {
        MyabtisEntity myabtisEntity = myabtisController.selectById("1");
        Assert.assertNotNull(myabtisEntity);
        System.out.println(myabtisEntity);
    }


}
