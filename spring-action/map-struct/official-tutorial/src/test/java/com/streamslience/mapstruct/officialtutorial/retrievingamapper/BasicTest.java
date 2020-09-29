package com.streamslience.mapstruct.officialtutorial.retrievingamapper;

import com.streamslience.mapstruct.officialtutorial.retrievingamapper.domain.entity.UserEntity;
import com.streamslience.mapstruct.officialtutorial.retrievingamapper.domain.model.UserVO;
import com.streamslience.mapstruct.officialtutorial.retrievingamapper.mapper.IUserBasicMapper;
import org.junit.Assert;

/**
 * 索引映射器(Retrieving a mapper)单元测试
 *
 * @author StreamSlience
 * @date 2020-09-29 15:20
 */
public abstract class BasicTest {

    public abstract void run();

    public void convert(IUserBasicMapper iUserBasicMapper) {
        UserEntity userEntity = new UserEntity("测试", 100);

        UserVO userVO = iUserBasicMapper.from(userEntity);

        System.err.println(userEntity);
        System.err.println(userVO);

        Assert.assertEquals("年龄错误", userEntity.getAge(), userVO.getAge());
        Assert.assertEquals("姓名错误", userEntity.getName(), userVO.getName());
    }

}
