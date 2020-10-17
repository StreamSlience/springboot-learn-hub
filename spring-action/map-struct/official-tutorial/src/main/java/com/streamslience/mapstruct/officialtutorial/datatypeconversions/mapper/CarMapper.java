package com.streamslience.mapstruct.officialtutorial.datatypeconversions.mapper;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.Car;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.CarDto;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain.DateMapper;
import org.mapstruct.Mapper;

/**
 * 映射类型转换-调用其他映射器(Invoking other mappers)
 */
@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface CarMapper {
    CarDto carToCarDto(Car car);
}
