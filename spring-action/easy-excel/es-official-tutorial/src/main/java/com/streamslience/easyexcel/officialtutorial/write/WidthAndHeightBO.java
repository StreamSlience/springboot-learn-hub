package com.streamslience.easyexcel.officialtutorial.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * <h2>列宽 和 行高 设定</h2>
 *
 * @author StreamSlience
 * @date 2020-11-02 21:26
 */
@Data
//@ContentRowHeight(10)
//@HeadRowHeight(20)
//@ColumnWidth(25)
public class WidthAndHeightBO {

    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;

    /**
     * 宽度为50,覆盖上面的宽度25
     */
    //@ColumnWidth(50)
    @ExcelProperty("数字标题")
    private Double doubleData;
}
