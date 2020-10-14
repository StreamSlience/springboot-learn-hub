package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import lombok.Data;

/**
 * @author StreamSlience
 * @date 2020-10-14 20:54
 */
@Data
public class Interior {

    private String ornament;

    public Interior() {
    }

    public Interior(String ornament) {
        this.ornament = ornament;
    }
}
