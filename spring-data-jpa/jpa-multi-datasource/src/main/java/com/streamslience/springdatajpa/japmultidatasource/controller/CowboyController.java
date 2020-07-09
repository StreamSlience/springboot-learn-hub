package com.streamslience.springdatajpa.japmultidatasource.controller;

import com.streamslience.springdatajpa.japmultidatasource.entity.primary.Cowboy;
import com.streamslience.springdatajpa.japmultidatasource.service.ICowboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author StreamSlience
 * @date 2020-06-25 10:21
 */
@Validated
@RestController
@RequestMapping("/cowboy")
public class CowboyController {

    @Autowired
    private ICowboyService iCowboyService;

    @GetMapping("/{id}")
    public Cowboy getInfo(@PathVariable Long id) {
        return iCowboyService.getInfo(id);
    }

}
