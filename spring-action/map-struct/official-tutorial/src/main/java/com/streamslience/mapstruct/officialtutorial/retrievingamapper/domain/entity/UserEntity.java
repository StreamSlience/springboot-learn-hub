package com.streamslience.mapstruct.officialtutorial.retrievingamapper.domain.entity;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author StreamSlience
 * @date 2020-09-29 14:31
 */
@Data
public class UserEntity {

    private String name;

    private Integer age;

    public UserEntity() {
    }

    public UserEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
