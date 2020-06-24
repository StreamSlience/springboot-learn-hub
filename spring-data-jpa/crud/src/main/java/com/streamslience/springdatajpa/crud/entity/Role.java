package com.streamslience.springdatajpa.crud.entity;


import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;


/**
 * Role实体类
 *
 * @author StreamSlience
 * @date 2020-06-14 17:00
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "role")
@Proxy(lazy = false)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Integer age;

    @Column
    private String homeAddress;

    /**
     * 声明{@code invalid}不需要与数据库的字段进行映射，在保存的时候不需要保存到数据库。
     * <p>
     * 等效的操作包括如下几种：
     * <p>
     * static String secrect; // not persistent because of static <br>
     * final String secrect = “Satish”; // not persistent because of final<br>
     * transient String secrect; // not persistent because of transient<br>
     */
    @Transient
    private String invalid;

    public Role() {
    }
}
