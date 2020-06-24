package com.streamslience.annotation.httprequest.requestmapping.methods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author StreamSlience
 * @date 2020-06-21 10:40
 */
@Slf4j
@RestController
public class GoodsController implements IGoodsController {

    @Override
    public GoodsBO get(String id) {
        log.info("get:[{}]", id);
        return new GoodsBO(id);
    }

    @Override
    public List<GoodsBO> getList(GoodsQry goodsQry) {
        log.info("get:[{}]", goodsQry);
        return new ArrayList<>();
    }

    @Override
    public Boolean delete(String id) {
        log.info("get:[{}]", id);
        return true;
    }
}
