package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import lombok.Data;

/**
 * 引用源对象
 *
 * @author StreamSlience
 * @date 2020-10-14 19:41
 */
@Data
public class ReferenceSource {

    private String value;

    public ReferenceSource() {
    }

    public ReferenceSource(String value) {
        this.value = value;
    }
}
