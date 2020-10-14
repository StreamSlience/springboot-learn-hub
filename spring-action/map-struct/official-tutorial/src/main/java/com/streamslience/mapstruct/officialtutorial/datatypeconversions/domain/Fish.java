package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import lombok.Data;

/**
 * @author StreamSlience
 * @date 2020-10-14 20:53
 */
@Data
public class Fish {

    private String kind = "kind";

    private String name;

    public Fish() {
    }

    public Fish(String kind, String name) {
        this.kind = kind;
        this.name = name;
    }
}
