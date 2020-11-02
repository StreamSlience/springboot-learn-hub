package com.streamslience.easyexcel.officialtutorial.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.streamslience.easyexcel.officialtutorial.read.CustomStr2Converter;
import lombok.Data;

import java.util.Date;


/**
 * <h2>自定义写入对象</h2>
 *
 * @author StreamSlience
 * @date 2020-11-02 20:56
 */
@Data
public class ConverterBO {

    @ExcelProperty(value = "字符串标题", converter = CustomStr2Converter.class)
    private String title;

    @DateTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
    @ExcelProperty("日期标题")
    private Date date;

    @NumberFormat("#.##%")
    @ExcelProperty(value = "数字标题")
    private Double doubleData;
}
