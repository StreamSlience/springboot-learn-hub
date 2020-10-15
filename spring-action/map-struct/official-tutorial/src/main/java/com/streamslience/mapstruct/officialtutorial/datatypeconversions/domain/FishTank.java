package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import lombok.Data;

/**
 * @author StreamSlience
 * @date 2020-10-15 15:05
 */
@Data
public class FishTank {

    private Fish fish = new Fish("kind", "name", "type");

    private String material = "material";

    private Quality quality = new Quality();

    private int length = 10;

    private int width = 10;

    private int height = 10;

}
