package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Car {

    private Date value1;

    private String value2;

    public Car() {
    }

    public Car(Date value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}
