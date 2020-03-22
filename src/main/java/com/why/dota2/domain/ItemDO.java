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
@Table(name = "item")
public class ItemDO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3542735897172605765L;

    @Id
    @Column
    private int itemId;
    @Column
    private String name;
    @Column
    private int cost;
    @Column
    private int secretShop;
    @Column
    private int sideShop;
    @Column
    private int recipe;
    @Column
    private String localizedName;
}
