package com.streamslience.easyexcel.esdatabaseoperation.domain.entity;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("物料名称")
    @Column(columnDefinition = "VARCHAR(50) DEFAULT ''")
    private String goodName;

    @ApiModelProperty("物料编码")
    @Column(columnDefinition = "VARCHAR(100) DEFAULT ''")
    private String goodCode;

    @ApiModelProperty("物料描述")
    @Column(columnDefinition = "VARCHAR(500) DEFAULT ''")
    private String goodDesc;

    @ApiModelProperty("物料单位")
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer goodUnit;

}
