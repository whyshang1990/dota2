package com.why.dota2.boot.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HeroesDTO implements Serializable {
    private static final long serialVersionUID = -8818860069944661908L;
    private int status;
    private int count;
    private List<HeroDTO> heroes;
}
