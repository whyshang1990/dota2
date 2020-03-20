package com.why.dota2.task;

import com.why.dota2.domain.HeroDO;
import com.why.dota2.dto.BaseResult;
import com.why.dota2.dto.HeroesDTO;
import com.why.dota2.repository.HeroDao;
import com.why.dota2.util.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@EnableScheduling
@Component
public class HeroTask {
    private RestTemplate restTemplate;
    private HeroDao heroDao;

    @Autowired
    public HeroTask(RestTemplate restTemplate, HeroDao heroDao) {
        this.restTemplate = restTemplate;
        this.heroDao = heroDao;
    }

    @Scheduled(fixedDelay = 360000)
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void getHeroesByApi() {
        String heroUrl = "http://api.steampowered.com/IEconDOTA2_570/GetHeroes/v1?key=D6F68C98AFC62AF939A5A2DBA4BB07DD&language=zh-cn";
        ParameterizedTypeReference<BaseResult<HeroesDTO>> reference = new ParameterizedTypeReference<BaseResult<HeroesDTO>>(){};

        ResponseEntity<BaseResult<HeroesDTO>> responseEntity = restTemplate.exchange(heroUrl, HttpMethod.GET, null, reference);
        BaseResult<HeroesDTO> baseResult = responseEntity.getBody();
        // log.info("test");
        if (baseResult != null) {
            HeroesDTO heroesDTO = baseResult.getResult();
            if (heroesDTO.getStatus() == 200) {
                List<HeroDO> heroDOList = heroesDTO.getHeroes().stream()
                        .map(heroDTO -> EntityUtils.DTO2DO(heroDTO, HeroDO.class))
                        .collect(Collectors.toList());
                heroDao.saveAll(heroDOList);
            }
        }
    }
}
