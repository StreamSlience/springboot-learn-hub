package com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers.domain;

import lombok.Data;

@Data
public class GermanRelease {

    private String title;

    public GermanRelease() {
    }

    public GermanRelease(String title) {
        this.title = title;
    }
}
