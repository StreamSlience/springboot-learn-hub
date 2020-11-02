package com.streamslience.easyexcel.officialtutorial.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author StreamSlience
 * @date 2020-11-02 12:38
 */
@Data
@Slf4j
public class ReadListener extends AnalysisEventListener<ReadBO> {

    List<ReadBO> list = new ArrayList<>();

    /**
     * 解析每一条数据都会调用该方法
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(ReadBO data, AnalysisContext context) {
        log.info("解析到一条数据：{}", JSON.toJSONString(data));
        list.add(data);
    }

    /**
     * 所有数据解析完毕都会调用该方法
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完毕：{}", JSON.toJSONString(list));
    }

}
