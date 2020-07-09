package com.streamslience.springdatajpa.japmultidatasource.controller;

import com.streamslience.springdatajpa.japmultidatasource.entity.secondary.Vega;
import com.streamslience.springdatajpa.japmultidatasource.service.IVegeService;
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
@RequestMapping("/vega")
public class VegaController {

    @Autowired
    private IVegeService iVegeService;

    @GetMapping("/{id}")
    public Vega getInfo(@PathVariable Long id) {
        return iVegeService.getInfo(id);
    }
}
