package com.streamslience.easyexcel.officialtutorial;

import com.alibaba.excel.EasyExcel;
import com.streamslience.easyexcel.officialtutorial.feelfast.FeelFastListener;
import com.streamslience.easyexcel.officialtutorial.feelfast.FeelFastBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * EasyExcel快速感受
 *
 * @author StreamSlience
 * @date 2020-11-01 14:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class FeelFastTest {

    private static final String FILE_NAME = System.getProperty("user.dir") + "\\src\\main\\resources\\excel\\feelFast.xls";

    /**
     * 简单读取Excel文件
     */
    @Test
    public void read() {
        EasyExcel.read(FILE_NAME, FeelFastBO.class, new FeelFastListener()).sheet().doRead();
    }

    /**
     * 简单写入Excel文件
     */
    @Test
    public void write() {
        List<FeelFastBO> list = new ArrayList<>();
        IntStream.rangeClosed(0, 10).peek(i -> list.add(new FeelFastBO(String.format("字符串%02d", i), new Date(), i)));
        EasyExcel.write(FILE_NAME, FeelFastBO.class).sheet(1, "写入").doWrite(list);
    }

}
