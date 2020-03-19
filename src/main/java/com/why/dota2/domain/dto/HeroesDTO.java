package com.why.dota2.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class HeroesDTO {
    private int status;
    private int count;
    private List<HeroDTO> heroes;
}
