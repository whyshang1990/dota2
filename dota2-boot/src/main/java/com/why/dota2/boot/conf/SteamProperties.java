package com.why.dota2.boot.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取配置文件中，前缀为steam的属性，注入到当前类对应属性。
 * 默认无法读取spring.profiles.active之外的文件
 */
@Data
// 该注解读取配置文件中的属性，注入到当前类，prefix指定前缀，
@ConfigurationProperties(prefix = "steam", ignoreUnknownFields = false)
public class SteamProperties {
    private String key;
    private String language;
}
