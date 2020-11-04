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
    private Integer id;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @Column
    private Integer tenantId;

    @Column
    @TableLogic
    private Integer isDeleted;

}
