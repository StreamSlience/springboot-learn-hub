package com.streamslience.easyexcel.officialtutorial.read;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 使用EasyExcel#@ExcelProperty指定列下标
 *
 * @author StreamSlience
 * @date 2020-11-02 12:37
 */
@Data
public class Read2BO extends ReadBO {

    @ExcelProperty(index = 1)
    private Date date;

    @ExcelProperty(index = 0)
    private String title;

    @ExcelProperty(index = 2)
    private Double num;

}
