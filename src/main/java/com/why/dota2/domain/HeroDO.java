package com.why.dota2.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "hero")
public class HeroDO implements Serializable {
    private static final long serialVersionUID = 6443345974743321782L;
    @Id
    @Column(nullable = false, unique = true)
    private int id;
    @Column
    private String name;
    @Column
    private String localizedName;
}
