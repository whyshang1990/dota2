package com.why.dota2.boot.core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Component
public class MatchHistoryListener {
    // @KafkaListener(groupId = "matchHistory", topics = KafkaConsts.MATCH_HISTORY_TOPIC)
    public void consume(String record) {
        log.info("record: {}", record);
    }
}
