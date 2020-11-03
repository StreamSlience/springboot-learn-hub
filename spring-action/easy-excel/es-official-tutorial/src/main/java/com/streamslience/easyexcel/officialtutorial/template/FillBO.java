package com.streamslience.easyexcel.officialtutorial.template;

import lombok.Data;

/**
 * Excle写入模板对象
 *
 * @author StreamSlience
 * @date 2020-11-03 12:39
 */
@Data
public class FillBO {

    private String string;

    private Double number;

    public FillBO() {
    }

    public FillBO(String string, Double number) {
        this.string = string;
        this.number = number;
    }
}
