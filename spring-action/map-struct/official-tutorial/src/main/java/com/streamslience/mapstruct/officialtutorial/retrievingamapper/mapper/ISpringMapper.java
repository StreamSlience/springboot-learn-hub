package com.streamslience.mapstruct.officialtutorial.retrievingamapper.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author StreamSlience
 * @date 2020-09-29 15:14
 */
@Mapper(componentModel = "spring", uses = InfoMapper.class,
        injectionStrategy = InjectionStrategy.FIELD)
public interface ISpringMapper extends IUserBasicMapper {
}
