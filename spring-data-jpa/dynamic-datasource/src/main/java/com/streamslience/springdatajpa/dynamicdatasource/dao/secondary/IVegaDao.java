package com.streamslience.springdatajpa.dynamicdatasource.dao.secondary;

import com.streamslience.springdatajpa.dynamicdatasource.entity.secondary.Vega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author StreamSlience
 * @date 2020-06-24 16:43
 */
@Repository
public interface IVegaDao extends JpaRepository<Vega, Long> {

}
