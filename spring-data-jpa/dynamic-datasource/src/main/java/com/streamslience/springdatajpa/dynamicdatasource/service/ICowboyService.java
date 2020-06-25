package com.streamslience.springdatajpa.dynamicdatasource.service;

import com.streamslience.springdatajpa.dynamicdatasource.entity.primary.Cowboy;

/**
 * @author StreamSlience
 * @date 2020-06-25 10:27
 */
public interface ICowboyService {
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return Cowboy
     */
    Cowboy getInfo(Long id);
}
