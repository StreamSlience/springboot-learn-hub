package com.streamslience.easyexcel.esdatabaseoperation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 实体基类
 *
 * @author StreamSlience
 * @date 2020-11-04 11:53
 */
@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @TableId
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;

    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT NOW()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @Column(nullable = false,columnDefinition = "VARCHAR(50) NOT NULL DEFAULT ''")
    private String tenantId;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    @TableLogic
    private Integer isDeleted;

}
