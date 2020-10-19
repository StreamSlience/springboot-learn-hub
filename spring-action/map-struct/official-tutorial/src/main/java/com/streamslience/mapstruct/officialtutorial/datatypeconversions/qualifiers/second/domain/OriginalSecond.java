package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain;

import lombok.Data;

@Data
public class OriginalSecond {

    private String title;

    public OriginalSecond() {
    }

    public OriginalSecond(String title) {
        this.title = title;
    }
}
