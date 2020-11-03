package com.streamslience.easyexcel.officialtutorial;

import com.alibaba.excel.EasyExcel;
import com.streamslience.easyexcel.officialtutorial.template.FillBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author StreamSlience
 * @date 2020-11-03 12:40
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OfficialTutorialApplication.class)
public class TemplateTest {

    /**
     *
     */
    @Test
    public void simpleFill(){
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        String templateFileName = "simple.xls";

        // 方案1 根据对象填充
        String fileName = System.currentTimeMillis() + ".xls";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        FillBO fillBO = new FillBO();
        fillBO.setString("知春秋");
        fillBO.setNumber(25D);
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillBO);

        // 方案2 根据Map填充
        fileName = System.currentTimeMillis() + ".xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        Map<String, Object> map = new HashMap<>();
        map.put("name", "知春秋");
        map.put("number", 25);
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);

    }

}
