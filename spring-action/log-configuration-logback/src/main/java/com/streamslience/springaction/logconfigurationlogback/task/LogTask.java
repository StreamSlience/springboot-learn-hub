package com.streamslience.springaction.logconfigurationlogback.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author StreamSlience
 * @date 2020-06-28 14:54
 */
@Slf4j
@Component
public class LogTask {

    @Scheduled(cron = "0/10 * * * * ?")
    public void logTask() {
        log.info("OutPut-debug:{}",System.currentTimeMillis());
    }

}
