package com.streamslience.springaction.logconfigurationlogback.controller;

import com.streamslience.springaction.logconfigurationlogback.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>SpringBoot中获取日志对象的方式如下：
 * <br>1.使用Lombok提供的@Slf4j注解
 * <br>2.使用LoggerFactory创建日志对象
 * <br>注意:两种方式创建的日志对象其实就是同一个。
 *
 * @author StreamSlience
 * @date 2020-06-28 11:28
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @GetMapping("/output")
    public String output() {
        LOGGER.info("OutPut-info:{}", System.currentTimeMillis());
        LOGGER.error("OutPut-error:{}", System.currentTimeMillis());
        LOGGER.debug("OutPut-debug:{}", System.currentTimeMillis());
        LOGGER.warn("OutPut-warn:{}", System.currentTimeMillis());
        LOGGER.trace("OutPut-trace:{}", System.currentTimeMillis());
        log.info("log.equals(LOGGER):{}", log.equals(LOGGER));

        return logService.output();
    }

}
