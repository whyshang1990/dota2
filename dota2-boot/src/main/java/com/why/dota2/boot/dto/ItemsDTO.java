package com.why.dota2.boot.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemsDTO {
    private List<ItemDTO> items;
    private int status;
}
