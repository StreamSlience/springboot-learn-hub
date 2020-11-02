package com.streamslience.easyexcel.officialtutorial.feelfast;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 * 注意：读写反射对象用到了Cglib，因此变量命名需要遵循驼峰规范，
 * 并且 使用Lombok#@Data不能同时Lombok#@Accessors(chain = true)，
 * 否则将会导致解析得到数据为空。
 *
 *
 * @author StreamSlience
 * @date 2020-11-01 14:57
 */
@Data
@Accessors(chain = true)
public class FeelFastBO {

    @ExcelProperty(index = 0)
    @ApiModelProperty("字符串标题")
    private String title;

    @ExcelProperty(index = 1)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty("日期标题")
    private Date date;

    @ExcelProperty(index = 2)
    @ApiModelProperty("数值标题")
    private Integer num;

    public FeelFastBO() {
    }

    public FeelFastBO(String title, Date date, Integer num) {
        this.title = title;
        this.date = date;
        this.num = num;
    }
}
