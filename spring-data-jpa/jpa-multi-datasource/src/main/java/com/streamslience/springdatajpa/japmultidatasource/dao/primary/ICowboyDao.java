package com.streamslience.springdatajpa.japmultidatasource.dao.primary;

import com.streamslience.springdatajpa.japmultidatasource.entity.primary.Cowboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author StreamSlience
 * @date 2020-06-24 16:43
 */
@Repository
public interface ICowboyDao extends JpaRepository<Cowboy, Long> {

}
