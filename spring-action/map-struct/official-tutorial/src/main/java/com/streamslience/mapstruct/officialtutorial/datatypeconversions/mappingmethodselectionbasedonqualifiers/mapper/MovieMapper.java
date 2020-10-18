package com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers.mapper;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers.EnglishToGerman;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers.GermanToEnglish;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers.Titles;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers.domain.GermanRelease;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers.domain.OriginalRelease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = Titles.class)
public interface MovieMapper {

    @Mapping(target = "title",qualifiedBy = {GermanToEnglish.class, EnglishToGerman.class})
    //@Mapping(target = "title",qualifiedByName = {"GermanToEnglish", "EnglishToGerman"})
    GermanRelease toGerman(OriginalRelease movies);

}
