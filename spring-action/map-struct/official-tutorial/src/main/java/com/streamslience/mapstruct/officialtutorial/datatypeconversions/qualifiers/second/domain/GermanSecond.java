package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain;

import lombok.Data;

@Data
public class GermanSecond {

    private String title;

    public GermanSecond() {
    }

    public GermanSecond(String title) {
        this.title = title;
    }
}
