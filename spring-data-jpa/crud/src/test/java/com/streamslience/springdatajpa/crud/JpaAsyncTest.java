package com.streamslience.springdatajpa.crud;

import com.streamslience.springdatajpa.crud.dao.IRoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author StreamSlience
 * @date 2020-06-14 23:27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaAsyncTest {

    @Autowired
    private IRoleDao iRoleDao;

    @Test
    public void findAllAsync() {
        Long time = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            iRoleDao.findAll();
        }
        System.err.println(System.currentTimeMillis() - time);//耗时358

        time = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            iRoleDao.findAllAsync();
        }
        System.err.println(System.currentTimeMillis() - time);//耗时86
    }
}
