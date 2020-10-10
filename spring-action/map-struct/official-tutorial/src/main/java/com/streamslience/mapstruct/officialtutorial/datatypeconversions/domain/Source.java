package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import lombok.Data;

/**
 * 源对象
 *
 * @author StreamSlience
 * @date 2020-10-10 13:36
 */
@Data
public class Source {

    private int value1 = 1;

    private Integer value2 = 1;

    private int value3 = 1;

    private byte value4 = 1;

    private int value5 = 100;

    private Boolean value6 = true;

    /**
     * 枚举 和 String 之间可以自动转换，自动将枚举类型常量的名称赋值给对应的String类型字段
     */
    private Enums enums1 = Enums.A;

    /**
     * 指定枚举常量属性进行转换，需要使用@Mapping#expression进行指定转换方式
     */
    private Enums enums2 = Enums.A;

}
