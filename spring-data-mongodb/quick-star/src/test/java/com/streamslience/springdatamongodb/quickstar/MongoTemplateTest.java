package com.streamslience.springdatamongodb.quickstar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author StreamSlience
 * @date 2020-07-08 0:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTemplateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Article article;

    private List<Article> articles;

    @Before
    public void before() {
        mongoTemplate.dropCollection(Article.class);
        mongoTemplate.dropCollection("article_info_other");
        article = Article.builder()
                .author("streamslience")
                .title("MongoDB 学习")
                .tags(Arrays.asList("学习", "数据库"))
                .visitCount(999L)
                .url("www.xxx.com")
                .addTime(new Date())
                .build();

        articles = IntStream.iterate(0, operand -> ++operand).limit(10).mapToObj(action ->
                Article.builder()
                        .visitCount(System.currentTimeMillis())
                        .build()).collect(Collectors.toList());
    }

    private void clearId(){
        articles = articles.stream().peek(i -> i.setId("")).collect(Collectors.toList());
    }

    /**
     * {@link MongoTemplate#save(Object)} 保存文档到对象的默认集合,在实体对象上使用{@code @Document}指定集合
     * {@link MongoTemplate#save(Object, String)} 保存文档到指定集合,如果集合不存在则新建集合
     */
    @Test
    public void save() {
        mongoTemplate.save(article);
        mongoTemplate.save(article, "article_info_other");
    }

    /**
     * {@link MongoTemplate#insert(Object)} 同 {@link MongoTemplate#save(Object)} 功能一样
     * {@link MongoTemplate#insert(Object, String)} 同 {@link MongoTemplate#save(Object, String)} 功能一样
     * {@link MongoTemplate#insert(Collection, Class)} 批量写入实体对象,需要指定实体类
     * {@link MongoTemplate#insert(Collection, String)} 批量写入实体对象,需要集合名称,集合不存在则创建集合
     * {@link MongoTemplate#insertAll(Collection)} 将对象的混合集合插入数据库集合中，根据类确定要使用的集合名称。
     */
    @Test
    public void insert() {
        mongoTemplate.insert(article);
        mongoTemplate.insert(article, "article_info_other");
        mongoTemplate.insert(articles, Article.class);
        clearId();
        mongoTemplate.insert(articles, "article_info_other");
        clearId();
        mongoTemplate.insertAll(articles);
        clearId();
    }

}
