package com.why.dota2.core;

import com.why.dota2.constant.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
// @Component
public class MatchHistoryListener {
    // @KafkaListener(groupId = "matchHistory", topics = KafkaConsts.MATCH_HISTORY_TOPIC)
    public void consume(String record) {
        log.info("record: {}", record);
    }
}
