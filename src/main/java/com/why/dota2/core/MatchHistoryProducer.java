package com.why.dota2.core;

import com.why.dota2.constant.KafkaConsts;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MatchHistoryProducer {
    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String message) {
        kafkaTemplate.send(KafkaConsts.MATCH_HISTORY_TOPIC, message);
    }
}
