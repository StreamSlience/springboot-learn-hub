package com.streamslience.springdatajpa.dynamicdatasource.service;

import com.streamslience.springdatajpa.dynamicdatasource.entity.secondary.Vega;

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
