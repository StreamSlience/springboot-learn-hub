package com.streamslience.springaction.exceptionhandler.controller;

import com.streamslience.springaction.exceptionhandler.entity.UserEntity;
import com.streamslience.springaction.exceptionhandler.exception.BizException;
import com.streamslience.springaction.exceptionhandler.tool.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author StreamSlience
 * @date 2020-07-21 16:02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/{id}")
    public Result<UserEntity> get(@PathVariable("id") String id) {
        if ("0".equals(id)) {
            throw new NullPointerException("查询异常");
        }
        return Result.success(new UserEntity());
    }

    @GetMapping(value = "/add")
    public Result<Boolean> add() {
        throw new BizException("新增用户失败");
    }

}
