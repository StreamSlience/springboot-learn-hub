package com.streamslience.springdatamongodb.quickstart;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 *
 * {@code @Document}注解作用于实体对象,用于指定实体对象的集合
 *
 * {@code @Id} 指定文档的主键字段
 * {@code @Field} 指定文档的字段名
 * @author StreamSlience
 * @date 2020-07-08 0:12
 */
@Data
@Builder(toBuilder = true)
@Document("article_info")
public class Article {

    @Id
    @ApiModelProperty("主键")
    private String id;

    @Field("title")
    @ApiModelProperty("标题")
    private String title;

    @Field("url")
    @ApiModelProperty("链接")
    private String url;

    @Field("author")
    @ApiModelProperty("作者")
    private String author;

    @Field("tags")
    @ApiModelProperty("分类标签")
    private List<String> tags;

    @Field("visit_count")
    @ApiModelProperty("访问量")
    private Long visitCount;

    @Field("add_time")
    @ApiModelProperty("添加时间")
    private Date addTime;

}
