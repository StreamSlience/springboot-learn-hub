package com.streamslience.easyexcel.officialtutorial;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.streamslience.easyexcel.officialtutorial.read.Read1BO;
import com.streamslience.easyexcel.officialtutorial.read.Read2BO;
import com.streamslience.easyexcel.officialtutorial.read.Read3BO;
import com.streamslience.easyexcel.officialtutorial.read.ReadListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author StreamSlience
 * @date 2020-11-02 12:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class ReadTest {

    private static final String FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\excel\\read.xls";

    /**
     * 读取Excel 方法一
     * 读取完后文件流会自动关闭
     */
    @Test
    public void simpleRead1() {
        EasyExcel.read(FILE_NAME, Read1BO.class, new ReadListener()).sheet().doRead();
    }

    /**
     * 读取Excel 方法二
     * 必须调用ExcelReader#finish方法，因为读取的时候会创建临时文件，不关闭会到时磁盘崩盘。
     */
    @Test
    public void simpleRead2() {
        ExcelReader excelReader = EasyExcel.read(FILE_NAME, Read1BO.class, new ReadListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }

    /**
     * 使用EasyExcel#@ExcelProperty指定列下标或名称进行读取
     */
    @Test
    public void readSpecifyNameOrIndex() {
        EasyExcel.read(FILE_NAME, Read2BO.class, new ReadListener()).sheet().doRead();
        EasyExcel.read(FILE_NAME, Read3BO.class, new ReadListener()).sheet().doRead();
    }


}
