package com.streamslience.springdatajpa.multitablequery.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author StreamSlience
 * @date 2020-06-15 22:31
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String companyName;
    private String description;

    public Company(String name, String description) {
        this.companyName = name;
        this.description = description;
    }

}
