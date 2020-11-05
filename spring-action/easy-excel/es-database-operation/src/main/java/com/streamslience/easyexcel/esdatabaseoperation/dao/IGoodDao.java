package com.streamslience.easyexcel.esdatabaseoperation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.streamslience.easyexcel.esdatabaseoperation.domain.entity.GoodEntity;
import org.springframework.stereotype.Repository;


/**
 * 物料持久层接口
 *
 * @author StreamSlience
 * @date 2020-11-05 09:55
 */
@Repository
public interface IGoodDao extends BaseMapper<GoodEntity> {

}
