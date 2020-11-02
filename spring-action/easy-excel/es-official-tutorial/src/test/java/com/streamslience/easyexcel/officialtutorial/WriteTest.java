package com.streamslience.easyexcel.officialtutorial;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.streamslience.easyexcel.officialtutorial.write.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.IOP.ComponentIdHelper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.log.LogHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author StreamSlience
 * @date 2020-11-02 12:37
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class WriteTest {

    private static final String FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\excel\\";

    private static final String WRITE_NAME = FILE_NAME + "write";

    private static final String EXCLUDE_OR_INCLUDE_NAME = FILE_NAME + "excludeOrIncludeWrite";

    private static final String COMPLEX_HEAD_NAME = FILE_NAME + "complexHead";

    private static final String CONVERSION_NAME = FILE_NAME + "conversion";

    private static final String IMAGES_NAME = FILE_NAME + "images";

    private static final List<WriteBO> WRITE_BOS = new ArrayList<WriteBO>() {{
        add(new WriteBO("哈哈哈", new Date(), 1D));
        add(new WriteBO("嘿嘿嘿", new Date(), 2D));
        add(new WriteBO("嘻嘻嘻", new Date(), 3D));
    }};

    /**
     * 写入Excel 方法一
     */
    @Test
    public void simpleWrite1() {
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(WRITE_NAME + System.currentTimeMillis() + ".xls", WriteBO.class).sheet("写入方法一").doWrite(WRITE_BOS);
    }

    /**
     * 写入Excel 方法二
     */
    @Test
    public void simpleWrite2() {
        // 写法2，方法二需要手动关闭流
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(WRITE_NAME + System.currentTimeMillis() + ".xls", Write1BO.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("写入方法二").build();
        excelWriter.write(WRITE_BOS, writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 写入Exce 指定列 名称 和 顺序
     */
    @Test
    public void writeSpecifyNameAndIndex() {
        EasyExcel.write(WRITE_NAME + System.currentTimeMillis() + ".xls", Write1BO.class).sheet().doWrite(WRITE_BOS);
    }

    /**
     * 导出指定的列
     */
    @Test
    public void writeSpecifiedColumn() {
        log.info("指定列不导入");
        // 忽略 date 不导出
        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add("date");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(EXCLUDE_OR_INCLUDE_NAME + System.currentTimeMillis() + ".xls", Write1BO.class)
                .excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet("忽略date").doWrite(WRITE_BOS);

        log.info("指定列导入");
        // 根据用户传入字段 假设我们只要导出 date
        Set<String> includeColumnFiledNames = new HashSet<>();
        includeColumnFiledNames.add("date");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(EXCLUDE_OR_INCLUDE_NAME + System.currentTimeMillis() + ".xls", Write1BO.class)
                .includeColumnFiledNames(includeColumnFiledNames).sheet("导出date")
                .doWrite(WRITE_BOS);
    }

    /**
     * 复杂头写入
     */
    @Test
    public void writeComplexHead() {
        EasyExcel.write(COMPLEX_HEAD_NAME + System.currentTimeMillis() + ".xls", ComplexHeadBO.class).sheet("复杂头写入").doWrite(WRITE_BOS);
    }

    /**
     * 自定义格式转换写入
     */
    @Test
    public void writeCustomFormatConversion() {
        EasyExcel.write(CONVERSION_NAME + System.currentTimeMillis() + ".xls", ConverterBO.class)
                .sheet("自定义格式转换写入").doWrite(WRITE_BOS);
    }

    /**
     * 导出入图像
     */
    @Test
    public void writeImages() throws IOException {
        List<ImageBO> list = new ArrayList<ImageBO>() {{ add(new ImageBO()); }};
        String fileName = IMAGES_NAME + System.currentTimeMillis() + ".xls";
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\image.jpg";

        try (InputStream inputStream = FileUtils.openInputStream(new File(imagePath));) {
            // 放入五种类型的图片 根据实际使用只要选一种即可
            list.get(0).setByteArray(FileUtils.readFileToByteArray(new File(imagePath)));
            list.get(0).setFile(new File(imagePath));
            list.get(0).setString(imagePath);
            list.get(0).setInputStream(inputStream);
            list.get(0).setUrl(new URL("https://pic1.zhimg.com/80/v2-8e8e575baec14e75ed7f9ca614a784c5_720w.jpg?source=1940ef5c"));
            EasyExcel.write(fileName, ImageBO.class).sheet().doWrite(list);
        }
    }

}





































