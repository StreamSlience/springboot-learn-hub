package com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers.domain;

import lombok.Data;

@Data
public class OriginalRelease {

    private String title;

    public OriginalRelease() {
    }

    public OriginalRelease(String title) {
        this.title = title;
    }
}
