package com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.domain;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author StreamSlience
 * @date 2020-10-10 14:25
 */
@Getter
public enum Enums {

    /**
     * A
     */
    A("A1", "A2", 1),
    /**
     * B
     */
    B("B1", "B2", 2);

    private final String code;
    private final String desc;
    private final Integer num;

    Enums(String code, String desc, Integer num) {
        this.code = code;
        this.desc = desc;
        this.num = num;
    }

    public static Enums getByName(String name) {
        return Arrays.stream(Enums.values()).filter(i -> i.name().equals(name)).findFirst().orElse(null);
    }
}
