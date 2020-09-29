package com.streamslience.mapstruct.officialtutorial.retrievingamapper.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mappers工厂
 *
 * @author StreamSlience
 * @date 2020-09-29 14:39
 */
@Mapper(componentModel = "default")
public interface IMappersFactoryMapper extends IUserBasicMapper {

    IMappersFactoryMapper INSTANCE = Mappers.getMapper(IMappersFactoryMapper.class);

}
