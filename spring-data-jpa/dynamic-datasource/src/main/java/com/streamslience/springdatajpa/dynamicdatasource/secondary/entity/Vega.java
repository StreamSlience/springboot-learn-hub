package com.streamslience.springdatajpa.dynamicdatasource.secondary.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author StreamSlience
 * @date 2020-06-24 16:39
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "vega")
public class Vega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
