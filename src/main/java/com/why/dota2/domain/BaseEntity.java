package com.why.dota2.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * {@link MappedSuperclass} 不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
 * 不能再标注@Entity或@Table注解，也无需实现序列化接口。可以使用@Id @Column
 */
@Data
@MappedSuperclass
public class BaseEntity  {
    @Column(columnDefinition = "DATETIME")
    protected Date gmtCreated;

    @Column(columnDefinition = "DATETIME")
    protected Date gmtModified;
}
