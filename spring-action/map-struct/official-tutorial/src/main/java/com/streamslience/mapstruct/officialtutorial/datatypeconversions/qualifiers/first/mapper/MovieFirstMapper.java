package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.mapper;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.domain.GermanFirst;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.GermanToEnglish;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.domain.OriginalFirst;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.domain.TitlesFirst;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TitlesFirst.class)
public interface MovieFirstMapper {

    /**
     * EnglishToGerman.class, GermanToEnglish.class 两个只能指定一个，同时指定MapStruct编译不通过
     *
     * @param movies
     * @return
     */
    @Mapping(target = "title", qualifiedBy = {GermanToEnglish.class})
    GermanFirst toGerman(OriginalFirst movies);

}
