package com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.domain;

import lombok.Data;

/**
 * @author StreamSlience
 * @date 2020-10-15 15:08
 */
@Data
public class VolumeDto {

    private int volume;

    private String description;

    public VolumeDto() {
    }

    public VolumeDto(int volume, String description) {
        this.volume = volume;
        this.description = description;
    }
}
