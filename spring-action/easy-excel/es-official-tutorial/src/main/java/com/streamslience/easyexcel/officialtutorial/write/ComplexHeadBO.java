package com.streamslience.easyexcel.officialtutorial.write;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <h2>复杂头对象</h2>
 *
 * <br>生成的复杂头如下所示<br>
 * <table border="1">
 * <tr>
 * <td colspan="3" align="center">主标题</td>
 * </tr>
 * <tr>
 * <td>字符串标题</td>
 * <td>日期标题</td>
 * <td>数字标题</td>
 * </tr>
 * </table>
 *
 * @author StreamSlience
 * @date 2020-11-02 20:46
 */
@Data
public class ComplexHeadBO {

    @ExcelProperty({"主标题", "字符串标题"})
    private String string;

    @ExcelProperty({"主标题", "日期标题"})
    private Date date;

    @ExcelProperty({"主标题", "数字标题"})
    private Double doubleData;

}
