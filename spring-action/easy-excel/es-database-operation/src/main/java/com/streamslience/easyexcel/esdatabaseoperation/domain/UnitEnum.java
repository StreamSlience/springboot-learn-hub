package com.streamslience.easyexcel.esdatabaseoperation.domain;

import lombok.Getter;

import java.util.Arrays;

/**
 * 单位枚举
 *
 * @author StreamSlience
 * @date 2020-11-05 09:59
 */
@Getter
public enum UnitEnum {

    none(-1, "", ""),
    μm(0, "μm", "微米"),
    mm(1, "mm", "毫米"),
    cm(2, "mm", "厘米"),
    dm(3, "dm", "分米"),
    m(4, "m", "米"),
    km(5, "km", "千米"),
    ;

    /**
     * 标识
     */
    private Integer id;
    /**
     * 单位符号
     */
    private String code;
    /**
     * 单位名称
     */
    private String name;

    UnitEnum(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public static UnitEnum getEnumByCode(String code) {
        return Arrays.stream(UnitEnum.values()).filter(i -> code.equals(i.getCode())).findFirst().orElse(UnitEnum.none);
    }

    public static UnitEnum getEnumByName(String name) {
        return Arrays.stream(UnitEnum.values()).filter(i -> name.equals(i.getName())).findFirst().orElse(UnitEnum.none);
    }

}
