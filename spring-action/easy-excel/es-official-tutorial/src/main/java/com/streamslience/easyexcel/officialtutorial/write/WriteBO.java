package com.streamslience.easyexcel.officialtutorial.write;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <h2>Excel写入基对象</h2>
 *
 * <b>注意：</b>
 * 必须指定{@link ExcelProperty#value()}属性。该属性用于设定Excel列的行头名称，
 * 如果不进行指定，则默认使用变量名作为行头名称。
 *
 * @author StreamSlience
 * @date 2020-11-02 19:56
 */
@Data
public class WriteBO {

    private String title;

    private Date date;

    private Double num;

    public WriteBO() {
    }

    public WriteBO(String title, Date date, Double num) {
        this.title = title;
        this.date = date;
        this.num = num;
    }
}
