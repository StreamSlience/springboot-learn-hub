package com.streamslience.easyexcel.officialtutorial.write;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author StreamSlience
 * @date 2020-11-02 20:19
 */
@Data
public class Write2BO {

    @ExcelProperty(value = "字符串标题")
    private String title;

    @ExcelProperty(value = "日期标题")
    private Date date;

    @ExcelProperty(value = "数值标题")
    private Double num;

}
