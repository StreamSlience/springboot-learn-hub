package com.streamslience.easyexcel.officialtutorial.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;
import lombok.Data;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * <h2>导入图像</h2>
 *
 * @author StreamSlience
 * @date 2020-11-02 21:05
 */
@Data
@ContentRowHeight(200)
@ColumnWidth(45)
public class ImageBO {

    private File file;

    private InputStream inputStream;

    /**
     * 如果string类型 必须指定转换器，string默认转换成string，该转换器是官方支持的
     */
    @ExcelProperty(converter = StringImageConverter.class)
    private String string;

    private byte[] byteArray;

    /**
     * 根据url导出 版本2.1.1才支持该种模式
     */
    private URL url;
}
