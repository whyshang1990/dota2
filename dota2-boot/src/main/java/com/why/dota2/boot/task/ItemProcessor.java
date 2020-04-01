package com.why.dota2.boot.task;

import com.why.dota2.boot.constant.SteamApiConsts;
import com.why.dota2.boot.domain.ItemDO;
import com.why.dota2.boot.dto.BaseResult;
import com.why.dota2.boot.dto.ItemsDTO;
import com.why.dota2.boot.repository.ItemDao;
import com.why.dota2.boot.util.EntityUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemProcessor extends CommonApiProcessor<BaseResult<ItemsDTO>> {
    @Resource
    private ItemDao itemDao;

    @Override
    protected String buildUrl() {
        return UriComponentsBuilder.fromHttpUrl(SteamApiConsts.GET_ITEMS)
                .queryParam(SteamApiConsts.PARAM_KEY, steamProperties.getKey())
                .queryParam(SteamApiConsts.PARAM_LANGUAGE, steamProperties.getLanguage())
                .build().encode().toString();
    }

    @Override
    protected ParameterizedTypeReference<BaseResult<ItemsDTO>> buildReference() {
        return new ParameterizedTypeReference<BaseResult<ItemsDTO>>(){};
    }

    @Override
    protected void save(BaseResult<ItemsDTO> baseResult) {
        if (baseResult != null) {
            ItemsDTO itemsDTO = baseResult.getResult();
            if (itemsDTO.getStatus() == 200) {
                List<ItemDO> itemDTOList = itemsDTO.getItems().stream()
                        .map(itemDTO -> {
                            ItemDO itemDO = EntityUtils.DTO2DO(itemDTO, ItemDO.class);
                            itemDO.setGmtCreated(new Date());
                            return itemDO;
                        })
                        .collect(Collectors.toList());
                itemDao.saveAll(itemDTOList);
            }
        }
    }
}
