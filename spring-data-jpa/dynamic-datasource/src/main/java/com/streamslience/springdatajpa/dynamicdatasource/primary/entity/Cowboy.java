package com.streamslience.springdatajpa.dynamicdatasource.primary.entity;

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
@Table(name = "cowboy")
public class Cowboy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}
