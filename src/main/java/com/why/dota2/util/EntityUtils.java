package com.why.dota2.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public class EntityUtils {
    public static <T> T DTO2DO(Object dtoEntity, Class<T> doClass) {
        if (dtoEntity == null || doClass == null) {
            return null;
        }
        try {
            T doClassInstance = doClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dtoEntity, doClassInstance);
            return doClassInstance;
        } catch (Exception e) {
            log.info("DTO to DO 转换失败({} to {})", dtoEntity.getClass().getName(), doClass.getName());
            return null;
        }
    }
}
