package com.why.dota2.boot.task;

import com.why.dota2.boot.constant.SteamApiConsts;
import com.why.dota2.boot.domain.HeroDO;
import com.why.dota2.boot.dto.BaseResult;
import com.why.dota2.boot.dto.HeroesDTO;
import com.why.dota2.boot.repository.HeroDao;
import com.why.dota2.boot.util.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@EnableScheduling
@Component
public class HeroesProcessor extends CommonApiProcessor<BaseResult<HeroesDTO>> {
    @Resource
    private HeroDao heroDao;

    @Override
    protected String buildUrl() {
        return UriComponentsBuilder.fromHttpUrl(SteamApiConsts.GET_HEROES)
                    .queryParam(SteamApiConsts.PARAM_KEY, steamProperties.getKey())
                    .queryParam(SteamApiConsts.PARAM_LANGUAGE, steamProperties.getLanguage())
                    .build().encode().toString();
    }

    @Override
    protected ParameterizedTypeReference<BaseResult<HeroesDTO>> buildReference() {
        return new ParameterizedTypeReference<BaseResult<HeroesDTO>>(){};
    }

    @Override
    protected void save(BaseResult<HeroesDTO> baseResult) {
        if (baseResult != null) {
            HeroesDTO heroesDTO = baseResult.getResult();
            if (heroesDTO.getStatus() == 200) {
                List<HeroDO> heroDOList = heroesDTO.getHeroes().stream()
                    .map(heroDTO -> {
                        HeroDO heroDO = EntityUtils.DTO2DO(heroDTO, HeroDO.class);
                        heroDO.setGmtCreated(new Date());
                        return heroDO;
                    })
                    .collect(Collectors.toList());
                heroDao.saveAll(heroDOList);
            }
        }
    }
}
