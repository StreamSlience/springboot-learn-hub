package com.streamslience.easyexcel.esdatabaseoperation.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author StreamSlience
 * @date 2020-11-04 11:52
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class GoodEntity extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(50) DEFAULT ''")
    private String goodName;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer goodType;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT ''")
    private String goodCode;

    @Column(columnDefinition = "VARCHAR(500) DEFAULT ''")
    private String goodDesc;

}
