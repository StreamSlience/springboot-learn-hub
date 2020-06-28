package com.streamslience.springaction.logconfigurationlogback.controller;

import com.streamslience.springaction.logconfigurationlogback.entity.MyabtisEntity;
import com.streamslience.springaction.logconfigurationlogback.service.MyabtisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author StreamSlience
 * @date 2020-06-28 15:40
 */
@RestController
@RequestMapping("/mybatis")
public class MyabtisController {

    @Autowired
    private MyabtisService myabtisService;

    @GetMapping("/{id}")
    public MyabtisEntity selectById(@PathVariable String id) {
       return myabtisService.selectById(id);
    }

}
