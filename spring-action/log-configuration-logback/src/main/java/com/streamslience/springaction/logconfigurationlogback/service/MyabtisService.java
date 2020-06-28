package com.streamslience.springaction.logconfigurationlogback.service;

import com.streamslience.springaction.logconfigurationlogback.dao.IMybatisDao;
import com.streamslience.springaction.logconfigurationlogback.entity.MyabtisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author StreamSlience
 * @date 2020-06-28 15:42
 */
@Service
public class MyabtisService {

    @Autowired
    private IMybatisDao mybatisDao;


    public MyabtisEntity selectById(String id) {
       return mybatisDao.selectById(id);
    }

}
