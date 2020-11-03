package com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.mapper;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.domain.Car;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.domain.CarDto;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.command.domain.DateMapper;
import org.mapstruct.Mapper;

/**
 * 映射类型转换-调用其他映射器(Invoking other mappers)
 */
@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface ICarMapper {
    /**
     * 映射方法
     *
     * @param car
     * @return
     */
    CarDto carToCarDto(Car car);
}
