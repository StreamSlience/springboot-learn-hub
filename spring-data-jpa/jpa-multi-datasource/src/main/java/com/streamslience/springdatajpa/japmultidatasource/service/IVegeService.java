package com.streamslience.springdatajpa.japmultidatasource.service;

import com.streamslience.springdatajpa.japmultidatasource.entity.secondary.Vega;

/**
 * @author StreamSlience
 * @date 2020-06-25 10:27
 */
public interface IVegeService {
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return Vega
     */
    Vega getInfo(Long id);
}
