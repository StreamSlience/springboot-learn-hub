package com.streamslience.springdatajpa.japmultidatasource.service.impl;

import com.streamslience.springdatajpa.japmultidatasource.dao.primary.ICowboyDao;
import com.streamslience.springdatajpa.japmultidatasource.entity.primary.Cowboy;
import com.streamslience.springdatajpa.japmultidatasource.service.ICowboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author StreamSlience
 * @date 2020-06-25 10:27
 */
@Service
public class CowboyServiceImpl implements ICowboyService {
    @Autowired
    private ICowboyDao iCowboyDao;

    @Override
    public Cowboy getInfo(Long id) {
        return iCowboyDao.findById(id).orElse(new Cowboy());
    }
}
