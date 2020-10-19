package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.domain;

import lombok.Data;

@Data
public class OriginalFirst {

    private String title;

    public OriginalFirst() {
    }

    public OriginalFirst(String title) {
        this.title = title;
    }
}
