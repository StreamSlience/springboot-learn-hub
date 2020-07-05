package com.streamslience.springdataelasticsearch.simplelearn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * <h3>{@code @Document}说明</h3>
 * 作用于类级别,用于标记实体类为文档对象
 * <strong>indexName:</strong> 指定对应索引名称,存在以下条件限制:
 * <ul>
 * <li>只能使用小写</li>
 * <li>不能包含以下字符 \, /, *, ?, ", <, >, |, ` ` (space character), ,, #</li>
 * <li>不能以以下字符开头 -, _, +</li>
 * <li>不能设置为 . 或者 ..</li>
 * <li>不能超过 255 字节 (note it is bytes, so multi-byte characters will count towards the 255 limit
 * faster)</li>
 * <strong>type:</strong> 指定对应索引库中的类型
 * <strong>shards:</strong> 指定分片数量,默认为5
 * <strong>replicas:</strong> 指定副本数量,默认为1
 *
 * <h3>{@code @Id}说明</h3>
 * 作用于成员变量级别,标记一个字段为主键
 *
 * <h3>{@code @Field}说明</h3>
 * 作用于成员变量级别,标记为文档的字段,并指定字段的属性
 * <strong>type:</strong> 指定字段类型,默认为{@code FieldType.Auto}
 * <strong>index:</strong> 指定是否索引,默认为true
 * <strong>store:</strong> 指定是否存储,默认为false
 * <strong>analyzer:</strong> 指定分词器名称
 *
 * @author StreamSlience
 * @date 2020-06-30 1:11
 */
@Data
@ApiModel("用户")
@Document(indexName = "user_index", type = "user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ApiModelProperty(value = "账号")
    @Field(type = FieldType.Text)
    private String username;

    @ApiModelProperty(value = "密码")
    @Field(type = FieldType.Text)
    private String password;

    @ApiModelProperty(value = "昵称")
    @Field(type = FieldType.Text)
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    @Field(type = FieldType.Text)
    private String email;

    @ApiModelProperty(value = "状态（0：锁定，1：解锁）")
    @Field(type = FieldType.Integer)
    private Integer status;

    @ApiModelProperty(value = "创建人")
    @Field(type = FieldType.Text)
    private String createUser;

    @ApiModelProperty(value = "更新人")
    @Field(type = FieldType.Text)
    private String updateUser;

    @ApiModelProperty(value = "年龄")
    @Field(type = FieldType.Double)
    private Double age;

}