package com.streamslience.annotation.httprequest.requestmapping.headers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一般的headers参数中用来包含接口版本信息。
 * 这样做的优点在于可以清晰的了解接口的版本迭代情况
 * 分清接口的职责，减少URL的数量
 *
 * @author StreamSlience
 * @date 2020-06-21 20:29
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping(value = "/info/{name}", headers = {"api-version=1.0"})
    public String get(@PathVariable("name") String name) {
        return String.format("《%s》第%s版", name, "一");
    }

    @GetMapping(value = "/info/{name}", headers = {"api-version=2.0"})
    public String getNew(@PathVariable("name") String name) {
        return String.format("《%s》第%s版", name, "二");
    }

    @GetMapping(value = "/info/{name}", headers = {"api-version"})
    public String getAll(@PathVariable("name") String name) {
        return String.format("《%s》", name);
    }

}
