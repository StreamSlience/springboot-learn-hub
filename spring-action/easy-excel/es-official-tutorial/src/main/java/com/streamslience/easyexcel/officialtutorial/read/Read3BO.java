package com.streamslience.easyexcel.officialtutorial.read;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.naming.Name;
import java.util.Date;

/**
 * 使用EasyExcel#@ExcelProperty指定列名称进行读取
 *
 * @author StreamSlience
 * @date 2020-11-02 12:37
 */
@Data
public class Read3BO extends ReadBO {

    @ExcelProperty(value = "日期标题")
    private Date date;

    @ExcelProperty(value = "字符串标题")
    private String title;

    @ExcelProperty(value = "数值标题")
    private Double num;

}
