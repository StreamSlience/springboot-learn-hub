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
 * @date 2020-07-09 22:02
 */
@Data
@Builder(toBuilder = true)
@Document(collection = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -7229906944062898852L;

    @Id
    @ApiModelProperty("主键")
    private String id;

    @Field("user_name")
    @ApiModelProperty("用户名")
    private String username;

    @Field("age")
    @ApiModelProperty("年龄")
    private Integer age;

    @Field("register_time")
    @ApiModelProperty("注册时间")
    private Instant registerTime;

}
