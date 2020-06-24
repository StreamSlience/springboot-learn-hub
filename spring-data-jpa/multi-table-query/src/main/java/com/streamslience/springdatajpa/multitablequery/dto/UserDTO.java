package com.streamslience.springdatajpa.multitablequery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询实体
 *
 * @author StreamSlience
 * @date 2020-06-15 22:36
 */
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
public class UserDTO {
    private String name;
    private Integer age;
    private String companyName;
    private String schoolName;
}
