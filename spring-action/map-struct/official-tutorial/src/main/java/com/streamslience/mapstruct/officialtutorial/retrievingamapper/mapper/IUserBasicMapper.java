package com.streamslience.mapstruct.officialtutorial.retrievingamapper.mapper;

import com.streamslience.mapstruct.officialtutorial.retrievingamapper.domain.entity.UserEntity;
import com.streamslience.mapstruct.officialtutorial.retrievingamapper.domain.model.UserVO;

/**
 * User - MapStruct接口
 *
 * @author StreamSlience
 * @date 2020-09-29 14:33
 */
public interface IUserBasicMapper {

    /**
     * UserEntity 转换为 UserVO
     *
     * @param userEntity UserEntity
     * @return UserVO
     */
    UserVO from(UserEntity userEntity);

}
