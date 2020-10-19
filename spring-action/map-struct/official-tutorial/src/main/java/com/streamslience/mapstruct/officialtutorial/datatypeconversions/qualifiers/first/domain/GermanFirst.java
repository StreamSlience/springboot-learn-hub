package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.domain;

import lombok.Data;

@Data
public class GermanFirst {

    private String title;

    public GermanFirst() {
    }

    public GermanFirst(String title) {
        this.title = title;
    }
}
