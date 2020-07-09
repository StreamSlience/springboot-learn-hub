package com.streamslience.springdatamongo.mongodbdatadatasource.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author StreamSlience
 * @date 2020-07-09 22:27
 */
@Data
@Builder(toBuilder = true)
@Document(collection = "login_log")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = -6694661682102504919L;

    @Id
    @ApiModelProperty("主键")
    private String id;

    @Field("u_id")
    @ApiModelProperty("用户主键")
    private String uid;

    @Field("user_name")
    @ApiModelProperty("用户名")
    private String username;

    @Field("login_time")
    @ApiModelProperty("登入时间")
    private Instant loginTime;

}
