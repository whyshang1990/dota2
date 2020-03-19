package com.why.dota2.domain.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 2550463194448311348L;

    private T result;
}
