package com.why.dota2.task;

import com.why.dota2.conf.SteamProperties;
import com.why.dota2.domain.HeroDO;
import com.why.dota2.dto.BaseResult;
import com.why.dota2.dto.HeroesDTO;
import com.why.dota2.repository.HeroDao;
import com.why.dota2.util.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@EnableScheduling
@Component
public class HeroTask {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private HeroDao heroDao;
    @Resource
    private SteamProperties steamProperties;

    @Scheduled(fixedDelay = 360000)
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void getHeroesByApi() {
        String heroUrl = "http://api.steampowered.com/IEconDOTA2_570/GetHeroes/v1?key={key}&language={language}";
        // 设置请求返回值类型，该类型为范型
        ParameterizedTypeReference<BaseResult<HeroesDTO>> reference =
                new ParameterizedTypeReference<BaseResult<HeroesDTO>>(){};
        // 设置请求参数字典，字典键为url中{}内的参数名，
        Map<String, String> params = new HashMap<>();
        params.put("key", steamProperties.getKey());
        params.put("language", steamProperties.getLanguage());

        // 调用url请求数据
        ResponseEntity<BaseResult<HeroesDTO>> responseEntity = restTemplate.exchange(
                heroUrl, HttpMethod.GET, null, reference, params);
        BaseResult<HeroesDTO> baseResult = responseEntity.getBody();

        if (baseResult != null) {
            HeroesDTO heroesDTO = baseResult.getResult();
            if (heroesDTO.getStatus() == 200) {
                // this.saveHeroes(heroesDTO);
                log.info("数据已存储");
            }
        }
    }

    private void saveHeroes(HeroesDTO heroesDTO) {
        List<HeroDO> heroDOList = heroesDTO.getHeroes().stream()
                .map(heroDTO -> EntityUtils.DTO2DO(heroDTO, HeroDO.class))
                .collect(Collectors.toList());
        heroDao.saveAll(heroDOList);
    }
}
