package com.streamslience.annotation.httprequest.requestmapping.methods;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author StreamSlience
 * @date 2020-06-21 10:42
 */
@RequestMapping(value = "/goods", method = RequestMethod.POST)
public interface IGoodsController {

    /**
     * 查询详情
     *
     * @param id 主键
     * @return 查询实体
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    GoodsBO get(@PathVariable(value = "id") String id);

    /**
     * 查询列表
     *
     * @param goodsQry 查询入参实体
     * @return 实体集合
     */
    @RequestMapping("/list")
    List<GoodsBO> getList(@RequestBody GoodsQry goodsQry);

    /**
     * 删除
     *
     * @param id 主键
     * @return 布尔类型
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Boolean delete(@PathVariable(value = "id") String id);

}
