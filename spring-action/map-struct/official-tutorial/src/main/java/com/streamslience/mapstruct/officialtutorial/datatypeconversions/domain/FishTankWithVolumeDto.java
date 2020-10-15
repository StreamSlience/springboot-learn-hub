package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import lombok.Data;

/**
 * @author StreamSlience
 * @date 2020-10-15 15:09
 */
@Data
public class FishTankWithVolumeDto {

    private FishDto fish;

    private MaterialDto material;

    private QualityDto quality;

    private VolumeDto volume;

}
