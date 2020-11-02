package com.streamslience.easyexcel.officialtutorial;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.streamslience.easyexcel.officialtutorial.read.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author StreamSlience
 * @date 2020-11-02 12:36
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class ReadTest {

    private static final String FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\excel\\";

    private static final String READ_NAME = FILE_NAME + "read.xls";

    private static final String READ_MULTIPLE_HEAD_NAME = FILE_NAME + "readMultipleHead.xls";

    private static final String READ_EXCEPTION_NAME = FILE_NAME + "readException.xls";

    /**
     * 读取Excel 方法一
     * 读取完后文件流会自动关闭
     */
    @Test
    public void simpleRead1() {
        EasyExcel.read(READ_NAME, Read1BO.class, new ReadListener()).sheet().doRead();
    }

    /**
     * 读取Excel 方法二
     * 必须调用ExcelReader#finish方法，因为读取的时候会创建临时文件，不关闭会到时磁盘崩盘。
     */
    @Test
    public void simpleRead2() {
        ExcelReader excelReader = EasyExcel.read(READ_NAME, Read1BO.class, new ReadListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }

    /**
     * 使用EasyExcel#@ExcelProperty指定列下标或名称进行读取
     */
    @Test
    public void readSpecifyNameOrIndex() {
        EasyExcel.read(READ_NAME, Read2BO.class, new ReadListener()).sheet().doRead();
        EasyExcel.read(READ_NAME, Read3BO.class, new ReadListener()).sheet().doRead();
    }

    /**
     * 读取指定多个sheet、一次性读取全部sheet
     */
    @Test
    public void readMultipleSheets() {
        /*
         * 读取全部sheet，注意：ReadListener#doAfterAllAnalysed方法会在每个sheet读取完毕后调用一次，
         * 然后所有sheet都会往同一个ReadListener里面写
         */
        log.info(">>>>>>>>>>>>>一次性读取全部sheet<<<<<<<<<<<<");
        log.info("方式一");
        EasyExcel.read(READ_NAME, ReadBO.class, new ReadListener()).doReadAll();

        log.info("方式二");
        ExcelReader excelReaderAll = EasyExcel.read(READ_NAME).build();
        ReadSheet readSheetAll = EasyExcel.readSheet().head(ReadBO.class).registerReadListener(new ReadListener()).build();
        excelReaderAll.read(readSheetAll);
        excelReaderAll.finish();

        /*
         * 读取部分sheet
         * 这里用户演示你就没有区分创建head和registerReadListener，
         * 在实际开发中可以更需求区分创建，比如季度财务报表根据季度划分为4个sheet，
         * 这里就每个sheet的列都是相同的因此只需要创建一组head和registerReadListener即可。
         *
         * 注意：1.必须同时将多个ReadSheet对象传入ReadSheet#read(ReadSheet...)方法，否则03版Excel会读取多次，浪费性能。
         *      2.程序最后必须执行ReadSheet#finish()方法，删除临时文件，方式磁盘崩盘。
         * */
        log.info(">>>>>>>>>>>>>多次读取部分sheet<<<<<<<<<<<<");
        ExcelReader excelReader = EasyExcel.read(READ_NAME).build();
        ReadSheet readSheet1 = EasyExcel.readSheet(0).head(ReadBO.class).registerReadListener(new ReadListener()).build();
        ReadSheet readSheet2 = EasyExcel.readSheet(1).head(ReadBO.class).registerReadListener(new ReadListener()).build();
        excelReader.read(readSheet1, readSheet2);
        excelReader.finish();
    }

    /**
     * 自定义格式转换
     */
    @Test
    public void readCustomFormatConversion() {
        EasyExcel.read(READ_NAME, Read4BO.class, new ReadListener()).sheet().doRead();
    }

    /**
     * 多行头 指定数据解释起始行
     */
    @Test
    public void readPeekMultipleHead() {
        EasyExcel.read(READ_MULTIPLE_HEAD_NAME, ReadBO.class, new ReadListener()).sheet().headRowNumber(2).doRead();
        EasyExcel.read(READ_MULTIPLE_HEAD_NAME, Read4BO.class, new ReadListener()).sheet().headRowNumber(0).doRead();
    }

    /**
     * 使用监听器 读取行头数据
     */
    @Test
    public void readHeadData() {
        EasyExcel.read(READ_NAME, ReadBO.class, new ReadHeadListener()).sheet().doRead();
        EasyExcel.read(READ_MULTIPLE_HEAD_NAME, Read4BO.class, new ReadHeadListener()).sheet().headRowNumber(2).doRead();
    }

    /**
     * 异常处理
     */
    @Test
    public void readExceptionDeal() {
        EasyExcel.read(READ_EXCEPTION_NAME, Read5BO.class, new ReadExceptionListener()).sheet().doRead();
    }

}




























