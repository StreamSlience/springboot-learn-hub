package com.streamslience.springdatajpa.dynamicdatasource;

import com.streamslience.springdatajpa.dynamicdatasource.dao.primary.ICowboyDao;
import com.streamslience.springdatajpa.dynamicdatasource.entity.primary.Cowboy;
import com.streamslience.springdatajpa.dynamicdatasource.dao.secondary.IVegaDao;
import com.streamslience.springdatajpa.dynamicdatasource.entity.secondary.Vega;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 数据源配置校验测试类
 *
 * @author StreamSlience
 * @date 2020-06-24 16:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CheckDataSourceTest {

    @Autowired
    private ICowboyDao iCowboyDao;

    @Autowired
    private IVegaDao iVegaDao;

    @Test
    public void smipleTest() {
        iCowboyDao.deleteAll();
        Cowboy cowboy = new Cowboy("牛郎");
        iCowboyDao.save(cowboy);
        Cowboy cowboy1 = iCowboyDao.findById(cowboy.getId()).orElse(new Cowboy());
        Assert.assertEquals("牛郎", cowboy1.getName());

        iVegaDao.deleteAll();
        Vega vega = new Vega("织女");
        iVegaDao.save(vega);
        Vega vega1 = iVegaDao.findById(vega.getId()).orElse(new Vega());
        Assert.assertEquals("织女", vega1.getName());
    }


}
