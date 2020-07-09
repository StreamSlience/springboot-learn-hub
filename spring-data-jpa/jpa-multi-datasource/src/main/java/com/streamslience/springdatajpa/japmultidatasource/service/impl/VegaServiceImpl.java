package com.streamslience.springdatajpa.japmultidatasource.service.impl;

import com.streamslience.springdatajpa.japmultidatasource.dao.secondary.IVegaDao;
import com.streamslience.springdatajpa.japmultidatasource.entity.secondary.Vega;
import com.streamslience.springdatajpa.japmultidatasource.service.IVegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author StreamSlience
 * @date 2020-06-25 10:28
 */
@Service
public class VegaServiceImpl implements IVegeService {

    @Autowired
    private IVegaDao iVegaDao;

    @Override
    public Vega getInfo(Long id) {
        return iVegaDao.findById(id).orElse(new Vega());
    }
}
