package com.why.dota2.boot.task;

import com.why.dota2.boot.constant.SteamApiConsts;
import com.why.dota2.boot.core.MatchHistoryProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;

/**
 * 调用 steam api 接口获取 match history json数据，发送到 kafka topic。
 */
@Slf4j
@Component
public class MatchHistoryProcessor extends CommonApiProcessor<String> {
    private static final int MATCHES_REQUESTED = 1;

    @Resource
    MatchHistoryProducer matchHistoryProducer;

    @Override
    protected String buildUrl() {
        return UriComponentsBuilder.fromHttpUrl(SteamApiConsts.GET_MATCH_HISTORY)
                .queryParam(SteamApiConsts.PARAM_KEY, steamProperties.getKey())
                .queryParam(SteamApiConsts.PARAM_MATCHES_REQUESTED, MATCHES_REQUESTED)
                .build().encode().toString();
    }

    @Override
    protected ParameterizedTypeReference<String> buildReference() {
        return new ParameterizedTypeReference<String>() {};
    }

    @Override
    protected void save(String baseResult) {
        log.info("开始处理接口返回信息");
        if (baseResult != null) {
            matchHistoryProducer.produce(baseResult);
        }
    }
}
