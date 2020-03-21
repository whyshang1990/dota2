package com.why.dota2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HeroDTO implements Serializable {
    private static final long serialVersionUID = -7611740355834331683L;

    private String name;
    @JsonProperty("id")
    private int heroId;
    @JsonProperty("localized_name")
    private String localizedName;
}
