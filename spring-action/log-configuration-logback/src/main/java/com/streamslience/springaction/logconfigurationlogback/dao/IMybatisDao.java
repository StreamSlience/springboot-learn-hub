package com.streamslience.springaction.logconfigurationlogback.dao;

import com.streamslience.springaction.logconfigurationlogback.entity.MyabtisEntity;
import org.springframework.stereotype.Repository;

/**
 * @author StreamSlience
 * @date 2020-06-28 15:46
 */
@Repository
public interface IMybatisDao {

    MyabtisEntity selectById(String id);

}
