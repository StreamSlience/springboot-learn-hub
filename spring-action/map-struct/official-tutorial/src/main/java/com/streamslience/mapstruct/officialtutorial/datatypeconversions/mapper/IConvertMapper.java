package com.streamslience.mapstruct.officialtutorial.datatypeconversions.mapper;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.Source;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.Target;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 转换接口
 *
 * @author StreamSlience
 * @date 2020-10-10 13:59
 */
@Mapper(componentModel = "spring")
public interface IConvertMapper {

    @Mapping(target = "enums2",expression = "java(source.getEnums2().getNum())")
    @Mapping(source = "value5", target = "value5", numberFormat = "$#.00")
    Target targetFromSource(Source source);

    List<Target> targetFromSource(List<Source> sources);

    @IterableMapping(numberFormat = "$#.00")
    List<String> stringsFromIntegers(List<Integer> integers);

}
