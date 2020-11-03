package com.streamslience.mapstruct.officialtutorial.retrievingamapper.mapper;

import com.streamslience.mapstruct.officialtutorial.retrievingamapper.domain.entity.InfoEntity;
import com.streamslience.mapstruct.officialtutorial.retrievingamapper.domain.model.InfoVO;
import org.mapstruct.Mapper;

/**
 * @author StreamSlience
 * @date 2020-10-09 15:53
 */
@Mapper(componentModel = "spring")
public interface InfoMapper {

    /**
     * 映射方法
     *
     * @param infoEntity
     * @return
     */
    InfoVO from(InfoEntity infoEntity);

}
