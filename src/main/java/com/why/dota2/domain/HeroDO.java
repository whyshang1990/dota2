package com.why.dota2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "hero")
public class HeroDO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6855170085356351236L;

    @Id
    @Column
    private int heroId;
    @Column
    private String name;
    @Column
    private String localizedName;
}
