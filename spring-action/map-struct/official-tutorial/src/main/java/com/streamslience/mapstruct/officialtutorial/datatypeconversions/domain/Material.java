package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import lombok.Data;

/**
 * @author StreamSlience
 * @date 2020-10-14 20:56
 */
@Data
public class Material {

    private String materialType;

    public Material() {
    }

    public Material(String materialType) {
        this.materialType = materialType;
    }
}
