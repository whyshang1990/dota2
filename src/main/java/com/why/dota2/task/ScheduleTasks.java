package com.why.dota2.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 执行API请求的定时任务类
 */
@EnableScheduling
@Component
public class ScheduleTasks {
    @Resource
    HeroesProcessor heroesProcessor;

    @Scheduled(fixedDelay = 360000)
    public void getHeroes() {
        heroesProcessor.process();
    }
}
