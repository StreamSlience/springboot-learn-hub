package com.streamslience.easyexcel.officialtutorial;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.streamslience.easyexcel.officialtutorial.write.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.generics.tree.VoidDescriptor;

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

    private static final String WIDTH_HEIGHT_NAME = FILE_NAME + "widthHeight";

    private static final String MERGE_NAME = FILE_NAME + "merge";

    private static final String DYNAMIC_HEAD_NAME = FILE_NAME + "dynamicHead";

    private static final List<WriteBO> WRITE_BOS = new ArrayList<WriteBO>() {{
        add(new WriteBO("哈哈哈", new Date(), 1D));
        add(new WriteBO("嘿嘿嘿", new Date(), 2D));
        add(new WriteBO("嘻嘻嘻", new Date(), 3D));
        add(new WriteBO("咻咻咻", new Date(), 4D));
        add(new WriteBO("哔哔哔", new Date(), 5D));
        add(new WriteBO("滴滴滴", new Date(), 6D));
        add(new WriteBO("哒哒哒", new Date(), 7D));
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
     * 写入Excel 指定列 名称 和 顺序
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
     * 导出图像
     */
    @Test
    public void writeImages() throws IOException {
        List<ImageBO> list = new ArrayList<ImageBO>() {{
            add(new ImageBO());
        }};
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

    /**
     * 导出数据 设定单元格大小
     */
    @Test
    public void writeWidthAndHeight() {
        EasyExcel.write(WIDTH_HEIGHT_NAME + System.currentTimeMillis() + ".xls", WidthAndHeightBO.class).sheet("设定行高和列宽").doWrite(WRITE_BOS);
    }

    /**
     * 合并单元格
     */
    @Test
    public void writeMerge() {
        String fileName = MERGE_NAME + System.currentTimeMillis() + ".xls";
        // 每隔2行会合并 把eachColumn 设置成 3 也就是我们数据的长度，所以就第一列会合并。当然其他合并策略也可以自己写
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, Write2BO.class).registerWriteHandler(loopMergeStrategy).sheet("合并单元格")
                .doWrite(WRITE_BOS);
    }

    /**
     * 动态表头
     */
    @Test
    public void writeDynamicHead() {
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);

        EasyExcel.write(DYNAMIC_HEAD_NAME + System.currentTimeMillis() + ".xls")
                // 这里放入动态头
                .head(list)
                .sheet("动态创建表头一")
                .sheetNo(0)
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(WRITE_BOS);

        EasyExcel.write(DYNAMIC_HEAD_NAME + System.currentTimeMillis() + ".xls")
                //不需要表头
                .needHead(false)
                .head(list)
                .sheet("动态创建表头二")
                .sheetNo(0)
                .doWrite(WRITE_BOS);

        EasyExcel.write(DYNAMIC_HEAD_NAME + System.currentTimeMillis() + ".xls")
                .head(Write1BO.class)
                //.head(list)
                .sheet("动态创建表头三")
                .sheetNo(0)
                .doWrite(WRITE_BOS);

        head0.add("咚咚咚");
        head1.add("12");
        head2.add("2020-10-10 10:10:10");

        EasyExcel.write(DYNAMIC_HEAD_NAME + System.currentTimeMillis() + ".xls")
                .head(list)
                .sheet("动态创建表头四")
                .sheetNo(0)
                .doWrite(new ArrayList());
    }

}





































