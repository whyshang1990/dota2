package com.why.dota2.task;

import com.why.dota2.constant.KafkaConsts;
import com.why.dota2.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 执行API请求的定时任务类
 */
@Slf4j
@EnableScheduling
@Component
public class ScheduleTasks {
    @Resource
    HeroesProcessor heroesProcessor;

    @Resource
    ItemProcessor itemProcessor;

    @Resource
    MatchHistoryProcessor matchHistoryProcessor;

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    // @Scheduled(fixedDelay = 360000)
    public void getHeroes() {
        heroesProcessor.process();
    }

    // @Scheduled(fixedDelay = 360000)
    public void getItems() {
        itemProcessor.process();
    }

    // @Scheduled(fixedDelay = 360000)
    public void getMatchesHistory() {
        matchHistoryProcessor.process();
    }

    @Scheduled(fixedDelay = 30000)
    protected void kafkaTest() {
        log.info("Kafka发送消息");
        kafkaTemplate.send(KafkaConsts.MATCH_HISTORY_TOPIC, "test: " + new Date().getTime());
    }
}
