package com.why.dota2.boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerDTO implements Serializable {
    private static final long serialVersionUID = 1783458288135489354L;

    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("player_slot")
    private Integer playerSlot;
    @JsonProperty("hero_id")
    private Integer heroId;
}
