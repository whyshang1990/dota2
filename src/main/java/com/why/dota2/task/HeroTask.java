package com.why.dota2.task;

import com.why.dota2.domain.common.BaseResult;
import com.why.dota2.domain.dto.HeroDTO;
import com.why.dota2.domain.dto.HeroesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableScheduling
@Component
public class HeroTask {
    private RestTemplate restTemplate;

    @Autowired
    public HeroTask(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedDelay = 30000)
    public void getHeroesByApi() {
        String heroUrl = "http://api.steampowered.com/IEconDOTA2_570/GetHeroes/v1?key=D6F68C98AFC62AF939A5A2DBA4BB07DD&language=zh-cn";
        ParameterizedTypeReference<BaseResult<HeroesDTO>> reference = new ParameterizedTypeReference<BaseResult<HeroesDTO>>(){};

        ResponseEntity<BaseResult<HeroesDTO>> responseEntity = restTemplate.exchange(heroUrl, HttpMethod.GET, null, reference);
        BaseResult<HeroesDTO> baseResult = responseEntity.getBody();
        // log.info("test");
        if (baseResult != null) {
            HeroesDTO heroesDTO = baseResult.getResult();
            if (heroesDTO.getStatus() == 200) {
                for (HeroDTO heroDTO : heroesDTO.getHeroes()) {
                    log.info("id: {}, name: {}, localized name: {}", heroDTO.getId(), heroDTO.getName(), heroDTO.getLocalizedName());
                }
            }
        }
    }
}
