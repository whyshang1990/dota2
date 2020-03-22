package com.why.dota2.task;

import com.why.dota2.constant.SteamApiConsts;
import com.why.dota2.domain.ItemDO;
import com.why.dota2.dto.BaseResult;
import com.why.dota2.dto.ItemsDTO;
import com.why.dota2.repository.ItemDao;
import com.why.dota2.util.EntityUtils;
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
