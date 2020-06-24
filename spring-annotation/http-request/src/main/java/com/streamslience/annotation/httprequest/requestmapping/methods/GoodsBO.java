package com.streamslience.annotation.httprequest.requestmapping.methods;

import lombok.Data;

/**
 * 该案例中直接将{@code GoodsBO}作为接口中的返回值,这里需要注意下{@code GoodsBO}中必须包含成员变量,
 * 并且符合bean对象规范,不然请求返回500错误。一般的开发中都活根据项目需求定义一个请求返回对象,将一些信息
 * (譬如 请求返回状态码 请求返回描述 请求返回的数据 等等)封装在其中进行返回。
 *
 * @author StreamSlience
 * @date 2020-06-21 10:43
 */
@Data
public class GoodsBO {

    private String id;

    public GoodsBO() {
    }

    public GoodsBO(String id) {
        this.id = id;
    }
}
