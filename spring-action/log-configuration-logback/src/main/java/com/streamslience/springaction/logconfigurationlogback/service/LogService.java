package com.streamslience.springaction.logconfigurationlogback.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author StreamSlience
 * @date 2020-06-28 12:21
 */
@Slf4j
@Service
public class LogService {

    public String output() {
        log.info("OutPut-info:{}", System.currentTimeMillis());
        log.error("OutPut-error:{}", System.currentTimeMillis());
        log.debug("OutPut-debug:{}", System.currentTimeMillis());
        log.warn("OutPut-warn:{}", System.currentTimeMillis());
        log.trace("OutPut-trace:{}", System.currentTimeMillis());
        return "success";
    }

}
