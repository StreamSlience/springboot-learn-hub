package com.streamslience.easyexcel.officialtutorial.read;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

/**
 * 自定义格式转换
 *
 * @author StreamSlience
 * @date 2020-11-02 12:37
 */
@Data
public class Read4BO extends ReadBO {

    @ExcelProperty(converter = CustomStr2Converter.class, index = 0)
    private String title1;

    @DateTimeFormat("自定义时间：yyyy年MM月dd日 HH时mm分ss秒")
    @ExcelProperty(index = 1)
    private String date1;

    @NumberFormat("#.##%")
    @ExcelProperty(index = 2)
    private String num1;

}
