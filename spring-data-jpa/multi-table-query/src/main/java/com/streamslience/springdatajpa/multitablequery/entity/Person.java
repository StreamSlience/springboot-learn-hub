package com.streamslience.springdatajpa.multitablequery.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Person实体类
 *
 * @author StreamSlience
 * @date 2020-06-15 23:00
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long schoolId;
    private Long companyId;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String homeAddress;
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}