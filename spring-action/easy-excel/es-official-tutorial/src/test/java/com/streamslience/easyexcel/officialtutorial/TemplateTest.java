package com.streamslience.easyexcel.officialtutorial;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.streamslience.easyexcel.officialtutorial.template.FillBO;
import com.streamslience.easyexcel.officialtutorial.write.WriteBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author StreamSlience
 * @date 2020-11-03 12:40
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class TemplateTest {

    private static final String FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\excel\\";

    private static final String SIMPLE_NAME = FILE_NAME + "template1.xls";

    private static final String COMPLEX_NAME = FILE_NAME + "template2.xls";

    private static final List<FillBO> FILL_BOS = new ArrayList<FillBO>() {{
        add(new FillBO("哈哈哈", 1D));
        add(new FillBO("嘿嘿嘿", 2D));
        add(new FillBO("嘻嘻嘻", 3D));
        add(new FillBO("咻咻咻", 4D));
        add(new FillBO("哔哔哔", 5D));
        add(new FillBO("滴滴滴", 6D));
        add(new FillBO("哒哒哒", 7D));
    }};

    /**
     * <h2>简单模板导入</h2>
     * <br><b>注意:</b>
     * 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
     */
    @Test
    public void simpleFill() {

        /*
         * 方案1 根据对象填充
         */
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        FillBO fillBO = new FillBO();
        fillBO.setString("妈咪妈咪哄");
        fillBO.setNumber(25D);
        EasyExcel.write(SIMPLE_NAME + System.currentTimeMillis() + ".xls")
                .withTemplate(SIMPLE_NAME).sheet().doFill(fillBO);

        /*
         * 方案2 根据Map填充
         * map中多个数据和少的数据都不会写入到excel中，
         * EasyExcel是根据map的key映射到Excel中的模板来确定和列的映射关系
         */
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        Map<String, Object> map = new HashMap<>();
        map.put("string", "妈咪妈咪哄");
        //map.put("number", 25);
        EasyExcel.write(SIMPLE_NAME + System.currentTimeMillis() + ".xls")
                .withTemplate(SIMPLE_NAME).sheet().doFill(map);

    }

    /**
     * <h2>复杂模板导入</h2>
     * <br><b>注意:</b>
     * {} 代表普通变量 {.} 代表是list的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
     */
    @Test
    public void complexFill() {
        ExcelWriter excelWriter = EasyExcel.write(COMPLEX_NAME + System.currentTimeMillis() + ".xls")
                .withTemplate(COMPLEX_NAME).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行
        // 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
        // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
        // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置
        // forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
        // 如果数据量大 list不是最后一行 参照下一个
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(FILL_BOS, fillConfig, writeSheet);
        excelWriter.fill(FILL_BOS, fillConfig, writeSheet);

        // 其他参数可以使用Map封装
//        Map<String, Object> map = new HashMap<>();
//        map.put("string",new ArrayList<String>(){{add("XXX");add("YYY");}});
//        map.put("number",new ArrayList<Integer>(){{add(111);add(222);}});
//        excelWriter.fill(map, writeSheet);
        excelWriter.finish();
    }

}
