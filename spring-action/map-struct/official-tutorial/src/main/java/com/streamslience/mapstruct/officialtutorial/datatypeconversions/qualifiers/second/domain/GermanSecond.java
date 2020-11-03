package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain;

import lombok.Data;

/**
 * @author StreamSlience
 * @date 2020-06-28 15:46
 */
@Data
public class GermanSecond {

    private String title;

    public GermanSecond() {
    }

    public GermanSecond(String title) {
        this.title = title;
    }
}
