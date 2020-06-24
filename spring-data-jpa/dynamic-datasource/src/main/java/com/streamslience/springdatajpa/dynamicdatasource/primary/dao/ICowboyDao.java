package com.streamslience.springdatajpa.dynamicdatasource.primary.dao;

import com.streamslience.springdatajpa.dynamicdatasource.primary.entity.Cowboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author StreamSlience
 * @date 2020-06-24 16:43
 */
@Repository
public interface ICowboyDao extends JpaRepository<Cowboy, Long> {

}
