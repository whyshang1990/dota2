package com.why.dota2.task;

import com.why.dota2.constant.SteamApiConsts;
import com.why.dota2.dto.BaseResult;
import com.why.dota2.dto.MatchHistoryDTO;
import com.why.dota2.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class MatchHistoryProcessor extends CommonApiProcessor<BaseResult<MatchHistoryDTO>> {
    private static final int MATCHES_REQUESTED = 1;

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
        if (baseResult != null) {
            MatchHistoryDTO matchHistoryDTO = baseResult.getResult();
            if (matchHistoryDTO.getStatus() == 1) {
                log.info("matches history info: {}", JsonUtils.toJson(matchHistoryDTO));
            }
        }
    }
}
