package com.streamslience.springdatajpa.japmultidatasource.entity.primary;

import lombok.Data;

import javax.persistence.*;

/**
 * @author StreamSlience
 * @date 2020-06-24 16:39
 */
@Data
@Entity
@Table(name = "cowboy")
public class Cowboy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Cowboy() {
    }

    public Cowboy(String name) {
        this.name = name;
    }

}
