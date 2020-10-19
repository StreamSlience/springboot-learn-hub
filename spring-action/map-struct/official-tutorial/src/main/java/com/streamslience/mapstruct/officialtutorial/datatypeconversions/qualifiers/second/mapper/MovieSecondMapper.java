package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.mapper;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain.GermanSecond;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain.OriginalSecond;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain.TitlesSecond;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TitlesSecond.class)
public interface MovieSecondMapper {

    /**
     * EnglishToGerman, GermanToEnglish 两个只能指定一个，同时指定MapStruct编译不通过
     *
     * @param movies
     * @return
     */
    @Mapping(target = "title", qualifiedByName = {"EnglishToGerman"})
    GermanSecond toGerman(OriginalSecond movies);

}
