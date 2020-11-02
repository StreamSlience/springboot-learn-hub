package com.streamslience.easyexcel.officialtutorial.read;

import lombok.Data;

import java.util.Date;

/**
 * 默认情况下下EasyExcel是根据顺序进行字段的映射同（即Bean对象中属性的自上而下 同 Excel中自左向右的列 一一对应），
 * 同变量的名称无关。因此在当前这种情况下，随意改变变量顺序会导致错误，可能刻意成功运行，那也仅仅是因为Excel中单元格类型恰巧
 * 同变量类型相同。
 *
 * @author StreamSlience
 * @date 2020-11-02 12:37
 */
@Data
public class Read1BO extends ReadBO {

//    private Date date;
//
//    private String title;
//
//    private Double num;

}
