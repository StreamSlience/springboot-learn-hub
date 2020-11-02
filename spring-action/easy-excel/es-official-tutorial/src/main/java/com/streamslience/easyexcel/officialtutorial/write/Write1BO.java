package com.streamslience.easyexcel.officialtutorial.write;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 默认情况下。EasyExcel是根据顺序进行字段的映射（即Bean对象中属性的自上而下 同 Excel中自左向右的列 一一对应）。
 * 如果希望指定写入顺序 和 对应的列名称 需要用到{@link ExcelProperty#value()} 和 {@link ExcelProperty#index()}两个属性
 *
 * @author StreamSlience
 * @date 2020-11-02 20:19
 */
@Data
public class Write1BO {

    @ExcelProperty(value = "字符串标题", index = 6)
    private String title;

    @ExcelProperty(value = "日期标题", index = 1)
    private Date date;

    @ExcelProperty(value = "数值标题", index = 0)
    private Double num;

}
