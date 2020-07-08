package com.streamslience.springdatamongodb.quickstar;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.ls.LSException;

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
                .id("99")
                .author("streamslience")
                .title("MongoDB 学习")
                .tags(Arrays.asList("学习", "数据库"))
                .visitCount(999L)
                .url("www.xxx.com")
                .addTime(new Date())
                .build();

        articles = IntStream.iterate(0, operand -> ++operand).limit(10).mapToObj(action ->
                Article.builder()
                        .id(action + "")
                        .visitCount(System.currentTimeMillis())
                        .build()).collect(Collectors.toList());
    }

    private void clearId() {
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

    /**
     * {@link MongoTemplate#remove(Query, Class)}、{@link MongoTemplate#remove(Query, String)}、{@link MongoTemplate#remove(Query, Class, String)}
     * 根据查询条件删除指定的文档。以下是查询条件的构造案例:
     * {@code Query.query(Criteria.where("_id").is("1").andOperator(Criteria.where("visit_count").is(1L).orOperator(Criteria.where("visit_count").is(2L))));}
     * <p>
     * {@link MongoTemplate#remove(Object)} 根据实体主键查询指定文档
     */
    @Test
    public void remove() {
        mongoTemplate.insertAll(articles);

        Article article = Article.builder().id("1").build();
        DeleteResult result = mongoTemplate.remove(article);
        Assert.assertEquals(1, result.getDeletedCount());

        Query query = Query.query(Criteria.where("_id").is("2"));
        result = mongoTemplate.remove(query, Article.class);
        Assert.assertEquals(1, result.getDeletedCount());

        query = Query.query(Criteria.where("visit_count").gt(1L));
        result = mongoTemplate.remove(query, "article_info");
        Assert.assertEquals(8, result.getDeletedCount());
    }

    /**
     * {@link MongoTemplate#updateFirst(Query, UpdateDefinition, Class)} 根据查询条件修改执行文档的指定字段。
     * 查询条件使用 {@link Query} 构建, 更新字段通过 {@link Update} 指定
     */
    @Test
    public void update() {
        mongoTemplate.insert(article);
        Query query = Query.query(Criteria.where("_id").is("99"));
        Update update = Update.update("title", "修改").set("visitCount", 10).rename("author", "authorName");
        UpdateResult result = mongoTemplate.updateFirst(query, update, Article.class);
    }

    /**
     * {@link MongoTemplate#findAll(Class)} 查询对应集合中的所有文档
     * {@link MongoTemplate#findById(Object, Class)} 根据主键查询指定集合中的文档
     */
    @Test
    public void query() {
        mongoTemplate.insertAll(articles);

        List<Article> articles = mongoTemplate.findAll(Article.class);
        articles.forEach(System.out::println);

        Article article = mongoTemplate.findById("1", Article.class);
        System.out.println(article);
    }
}



































