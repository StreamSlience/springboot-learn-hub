package com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingcollections.mapper;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingcollections.domain.Car;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingcollections.domain.CarDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author StreamSlience
 * @date 2020-10-20 19:57
 */
@Mapper
public interface CarMapper {

    Set<String> integerSetToStringSet(Set<Integer> integers);

    List<CarDto> carsToCarDtos(List<Car> cars);

    CarDto carToCarDto(Car car);

}
