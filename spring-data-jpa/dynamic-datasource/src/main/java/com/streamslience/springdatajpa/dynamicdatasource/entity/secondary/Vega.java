package com.streamslience.springdatajpa.dynamicdatasource.entity.secondary;

import lombok.Data;

import javax.persistence.*;

/**
 * @author StreamSlience
 * @date 2020-06-24 16:39
 */
@Data
@Entity
@Table(name = "vega")
public class Vega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Vega() {
    }

    public Vega(String name) {
        this.name = name;
    }
}
