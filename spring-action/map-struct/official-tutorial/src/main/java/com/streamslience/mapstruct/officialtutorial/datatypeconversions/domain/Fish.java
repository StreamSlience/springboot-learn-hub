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

    private String type;

    public Fish() {
    }

    public Fish(String kind, String name, String type) {
        this.kind = kind;
        this.name = name;
        this.type = type;
    }
}
