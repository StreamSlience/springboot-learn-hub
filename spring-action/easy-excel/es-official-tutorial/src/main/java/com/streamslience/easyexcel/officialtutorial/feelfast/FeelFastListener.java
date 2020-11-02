package com.streamslience.easyexcel.officialtutorial.feelfast;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author StreamSlience
 * @date 2020-11-01 15:00
 */
@Data
@Slf4j
public class FeelFastListener extends AnalysisEventListener<FeelFastBO> {

    /**
     * 解析每行数据的回调函数
     *
     * @param feelFastBO
     * @param analysisContext
     */
    @Override
    public void invoke(FeelFastBO feelFastBO, AnalysisContext analysisContext) {
        log.info("解析一行数据:{}", JSON.toJSONString(feelFastBO));
    }

    /**
     * 解析完所有数据后的回调函数
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完毕");
    }
}
