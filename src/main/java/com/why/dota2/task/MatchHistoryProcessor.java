package com.why.dota2.task;

import com.why.dota2.constant.KafkaConsts;
import com.why.dota2.constant.SteamApiConsts;
import com.why.dota2.core.MatchHistoryProducer;
import com.why.dota2.dto.BaseResult;
import com.why.dota2.dto.MatchHistoryDTO;
import com.why.dota2.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;

@Slf4j
@Component
public class MatchHistoryProcessor extends CommonApiProcessor<BaseResult<MatchHistoryDTO>> {
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
    protected ParameterizedTypeReference<BaseResult<MatchHistoryDTO>> buildReference() {
        return new ParameterizedTypeReference<BaseResult<MatchHistoryDTO>>() {};
    }

    @Override
    protected void save(BaseResult<MatchHistoryDTO> baseResult) {
        log.info("开始处理接口返回信息");
        if (baseResult != null) {
            MatchHistoryDTO matchHistoryDTO = baseResult.getResult();
            if (matchHistoryDTO.getStatus() == 1) {
                log.info("Kafka发送消息");
                matchHistoryProducer.produce(JsonUtils.toJson(matchHistoryDTO));
            }
        }
    }
}
