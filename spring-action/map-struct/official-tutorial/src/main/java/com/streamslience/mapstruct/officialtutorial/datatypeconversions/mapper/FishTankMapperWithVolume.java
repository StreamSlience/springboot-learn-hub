package com.streamslience.mapstruct.officialtutorial.datatypeconversions.mapper;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.FishTank;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.FishTankWithVolumeDto;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.VolumeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 映射类型转换-调用其他映射方法(Invoking custom mapping method)
 *
 * @author StreamSlience
 * @date 2020-10-15 15:14
 */
@Mapper(componentModel = "spring")
public abstract class FishTankMapperWithVolume {

    @Mapping(target = "fish.kind", source = "source.fish.type")
    @Mapping(target = "material.materialType", source = "source.material")
    @Mapping(target = "quality.document", source = "source.quality.report")
    @Mapping(target = "volume", source = "source")
    public abstract FishTankWithVolumeDto map(FishTank source);

    VolumeDto mapVolume(FishTank source) {
        int volume = source.getLength() * source.getWidth() * source.getHeight();
        String desc = volume < 100 ? "Small" : "Large";
        return new VolumeDto(volume, desc);
    }

}
