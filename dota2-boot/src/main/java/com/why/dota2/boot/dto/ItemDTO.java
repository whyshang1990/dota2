package com.why.dota2.boot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ItemDTO implements Serializable {
    private static final long serialVersionUID = -2873971760520740892L;
    @JsonProperty("id")
    private int itemId;
    private String name;
    private int cost;
    @JsonProperty("secret_shop")
    private int secretShop;
    @JsonProperty("side_shop")
    private int sideShop;
    private int recipe;
    @JsonProperty("localized_name")
    private String localizedName;
}
