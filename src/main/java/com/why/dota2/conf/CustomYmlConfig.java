package com.why.dota2.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义属性配置类
 */
@Configuration
public class CustomYmlConfig {
    @Bean
    public SteamProperties steamProperties() {
        return new SteamProperties();
    }
}
