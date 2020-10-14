package com.streamslience.mapstruct.officialtutorial.datatypeconversions.domain;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import lombok.Data;

import javax.print.attribute.standard.PrinterResolution;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 源对象
 *
 * @author StreamSlience
 * @date 2020-10-10 13:36
 */
@Data
public class Source {

    private int value1 = 1;

    private Integer value2 = 1;

    private int value3 = 1;

    private byte value4 = 1;

    private int value5 = 100;

    private Boolean value6 = true;

    /**
     * 大数类型 BigDecimal 和 BigInteger 可以自动转换为 String,
     * 但是必须使用 @Mapping#target 和 @Mapping#numberFormat 设置目标属性 和 转换格式
     */
    private BigDecimal value7 = new BigDecimal(1111);
    private BigInteger value8 = new BigInteger("2222");

    /**
     * java.util.Date/XMLGregorianCalendar 可以转换为 String
     */
    private Date value9 = new Date();
    private XMLGregorianCalendar value10 = new XMLGregorianCalendarImpl(new GregorianCalendar());

    /**
     * 枚举 和 String 之间可以自动转换，自动将枚举类型常量的名称赋值给对应的String类型字段
     */
    private Enums enums1 = Enums.A;

    /**
     * 指定枚举常量属性进行转换，需要使用@Mapping#expression进行指定转换方式
     */
    private Enums enums2 = Enums.A;

    /**
     * 根据枚举常量中特定的属性值转换为对应的枚举常量
     */
    private String enums3 = "A";

    /**
     * 引用对象 (源 和 目标 属性类型不同时 需要通过子映射方法进行映射)
     */
    private ReferenceSource referenceSource1 = new ReferenceSource("1");

    /**
     * 引用对象 (源 和 目标 属性类型相同时 直接赋值)
     *
     */
    private ReferenceSource referenceSource2 = new ReferenceSource("2");

    /**
     * 嵌套映射
     */
    private Fish fish = new Fish("kind","name");

    private Interior interior = new Interior("ornament");

    private Material material = new Material("material");

}






































