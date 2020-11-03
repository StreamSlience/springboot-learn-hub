package com.streamslience.springdataelasticsearch.simplelearn;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.InternalAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author StreamSlience
 * @date 2020-06-30 1:14
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/sys")
@Api(value = "User", tags = {"User"})
public class SysController {

    /**
     * 按照网上的教程一开始使用的是
     * {@code @Autowired}
     * private ElasticsearchTemplate elasticsearchTemplate;
     * 结果死活自动注入不了。
     * 看了ElasticsearchDataAutoConfiguration类
     * 发现名字为elasticsearchTemplate的bean对象注入的类型为ElasticsearchRestTemplate
     * 其实现了ElasticsearchOperations接口
     */
    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private UserRepository repository;

    @PostMapping("/{index}")
    @ApiOperation("创建索引")
    public Boolean createIndex(@PathVariable String index) {
        //不可以创建已经存在的索引
        return elasticsearchTemplate.createIndex(index);
    }

    @PostMapping("/createIndex")
    @ApiOperation("创建索引")
    public Boolean createIndex() {
        return elasticsearchTemplate.createIndex(SysUser.class);
    }

    @PostMapping("/deleteIndex/{index}")
    @ApiOperation("删除索引")
    public Boolean deleteIndex(@PathVariable String index) {
        return elasticsearchTemplate.deleteIndex(index);
    }

    @PostMapping("/save")
    @ApiOperation("新增")
    public SysUser save(@RequestBody SysUser user) {
        return repository.save(user);
    }

    @PostMapping("/saveAll")
    @ApiOperation("批量新增")
    public Iterable<SysUser> saveAll(@RequestBody List<SysUser> users) {
        return repository.saveAll(users);
    }

    @PostMapping("/findAllAndSort")
    @ApiOperation("查询全部并根据密码排序")
    public Iterable<SysUser> findAllAndSort() {
        return repository.findAll(Sort.by("password").ascending());
    }

    @PostMapping("/findAll")
    @ApiOperation("查询全部")
    public Iterable<SysUser> findAll() {
        return repository.findAll();
    }

    @PostMapping("/findByNickname")
    @ApiOperation("根据昵称查询用户")
    public List<SysUser> findByNickname(@RequestParam("nickname") String nickName) {
        List<SysUser> list = repository.findByNickname(nickName);
        return list;
    }

    @PostMapping("/findByNicknameOrPassword")
    @ApiOperation("根据昵称或者密码查询用户")
    public List<SysUser> findByNicknameOrPassword(@RequestParam("nickname") String nickName, @RequestParam("password") String password) {
        List<SysUser> list = repository.findByNicknameOrPassword(nickName, password);
        return list;
    }

    @PostMapping("/query")
    @ApiOperation("自定义查询")
    public Page<SysUser> query(@RequestParam("username") String userName) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.matchQuery("username", userName));
        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Page<SysUser> items = this.repository.search(builder.build());
        long total = items.getTotalElements();
        System.out.println("查询数量为:" + total);
        return items;
    }

    /**
     * 模糊查找
     *
     * @param userName
     * @return
     */
    @PostMapping("/fuzzyQuery")
    @ApiOperation("模糊查找根据分词去模糊，如果默认为5，输入4是没有办法模糊的")
    public Page<SysUser> fuzzyQuery(@RequestParam("username") String userName) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.fuzzyQuery("username", userName));
        // 查找
        Page<SysUser> page = this.repository.search(builder.build());
        return page;
    }

    /**
     * 根据列名进行聚合查询
     */
    @PostMapping("/aggregateQuery")
    @ApiOperation("根据列进行聚合查询")
    public void aggregateQuery(@RequestParam("clumname") String clumname) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 添加一个新的聚合，聚合类型为terms，聚合名称为列明，列名称为
        queryBuilder.addAggregation(
                AggregationBuilders.terms(clumname).field(clumname));
        // 将查询结果转换为聚合分页查询
        AggregatedPage<SysUser> aggPage = (AggregatedPage<SysUser>) this.repository.search(queryBuilder.build());
        StringTerms agg = (StringTerms) aggPage.getAggregation(clumname);
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即列名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的某列的数量
            System.out.println(bucket.getDocCount());
        }
    }

    @PostMapping("/arrregateAvg")
    @ApiOperation("根据列进行聚合查询求平均值")
    public void arrregateAvg(@RequestParam("clumname") String clumname) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));

        queryBuilder.addAggregation(AggregationBuilders.terms(clumname).field(clumname).subAggregation(AggregationBuilders.avg("ageAvg").field("age")));

        AggregatedPage aggPage = (AggregatedPage<SysUser>) repository.search(queryBuilder.build());

        StringTerms agg = (StringTerms) aggPage.getAggregation(clumname);
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();

        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            System.out.println(bucket.getKeyAsString() + ",共" + bucket.getDocCount() + "编");

            // 3.6.获取子聚合结果：
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("ageAvg");
            System.out.println("平均售价：" + avg.getValue());
        }

    }
}
