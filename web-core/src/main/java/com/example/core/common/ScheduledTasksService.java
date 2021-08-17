package com.example.core.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.Thread.sleep;

@Slf4j
@Component
public class ScheduledTasksService  {

    /**
     * fixedRate：固定速率执行。每5秒执行一次。
     */
    @Async("asyncServiceExecutor")
    @SneakyThrows
    @Scheduled(fixedRate = 3000*60)
    public void reportCurrentTimeWithFixedRate() {
        log.info("task 1 Current Thread : {}", Thread.currentThread().getName());
        sleep(4*1000);
        log.info("Fixed Rate Task : The time is now {}", new Date());
    }

    @Async("asyncServiceExecutor")
    @SneakyThrows
    @Scheduled(fixedRate = 3000*60)
    public void task2() {
        log.info("task 2 Current Thread : {}", Thread.currentThread().getName());
        sleep(10*1000);
        log.info("Fixed Rate Task : The time is now {}", new Date());
    }

}
