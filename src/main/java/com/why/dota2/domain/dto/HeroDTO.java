package com.why.dota2.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeroDTO {
    private String name;
    private String id;
    @JsonProperty("localized_name")
    private String localizedName;
}
