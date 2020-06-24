package com.streamslience.annotation.httprequest.requestmapping.params;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p><h3><strong>params功能综述</strong></h3>
 * <i>params元素</i> 的功能是帮助我们进步一缩小请求映射的指定范围。
 * 使用 <i>params元素</i> 可以让不同的处理方法映射到同一个URL请求，
 * 同时通过params指定参数进行区分。
 * (即请求路径的唯一性由 HTTP请求方式、URL 和 params元素 共同确定)
 * <p><strong>注意</strong>:
 * 该场景下请求类型为必须指定<strong>GET</strong>。
 * 即使用{@code @RequestMapping(method = RequestMethod.GET)}
 * 或者{@code @GetMapping()}
 *
 * <p>
 * <p>
 * <h3><strong>params元素的执行样式</strong></h3>
 * <li><p><Strong>myParam=myValue</Strong><br>
 * 只有在每个参数都有给定值时才映射请求。
 * <li><p><Strong>myParam!=myValue</Strong><br>
 * 不等于指定给定值就进行映射请求。
 * <li><p><Strong>myParam</Strong><br>
 * 表示myParam对应任何值都可以进行映射请求。
 * <p>
 * <p><strong>注意</strong>:<br>
 * 1.不允许出现!myParam样式的表达式，该样式表达式并不是想当然的表示:
 * 只要请求参数键名不为myParam的任意键就可以映射请求。<br>
 * 2.三种样式的映射优先级自上而下一次递减<br>
 *
 * @author StreamSlience
 * @date 2020-06-21 10:21
 */
@Slf4j
@RestController
@RequestMapping(value = "/person", method = RequestMethod.GET)
public class PersonController {

    /**
     * 样式：myParam=myValue
     *
     * @param id 主键
     * @return 字符串
     */
    @RequestMapping(value = "/info", params = {"personId=10"})
    String getParamsEqId(@RequestParam("personId") String id) {
        log.info("getParams:[{}]", id);
        return String.format("getParamsEqId:[%s]", id);
    }

    /**
     * 样式：myParam=myValue
     *
     * @param id 主键
     * @return 字符串
     */
    @RequestMapping(value = "/info", params = {"personId=20"})
    String getParamsEqIdDifferent(@RequestParam("personId") String id) {
        log.info("getParamsDifferent:[{}]", id);
        return String.format("getParamsEqIdDifferent:[%s]", id);
    }

    /**
     * 样式：myParam!=myValue
     * <p>
     * personId参数值不等于20就进行请求映射。
     * 需要注意的是: myParam=myValue 的映射优先级高于 myParam!=myValue
     * 比如，当我们指定 params=10进行请求时，同时满足映射条件的包括
     * {@link #getParamsEqId(String)}和 {@link #getParamsNeId(String)} 两个方法，
     * 但实际上优先映射到 {@link #getParamsEqId(String)}方法。和代码的先后顺序没有任何关系。
     *
     * @param id 主键
     * @return 字符串
     */
    @RequestMapping(value = "/info", params = {"personId!=20"})
    String getParamsNeId(@RequestParam("personId") String id) {
        log.info("getParamsNeId:[{}]", id);
        return String.format("getParamsNeId:[%s]", id);
    }


    /**
     * 样式：myParam
     * <p>
     * age参数可以是任意指定值(甚至可以仅传一个键，没有指定值)，
     * 但是age参数不能为null(即请求中不存在对应键名)。
     *
     * @param id 主键
     * @return 字符串
     */
    @RequestMapping(value = "/data", params = {"age"})
    String getParamsNoKey(@RequestParam("age") String id) {
        log.info("getParamsNoKey:[{}]", id);
        return String.format("getParamsNoKey:[%s]", id);
    }

}
