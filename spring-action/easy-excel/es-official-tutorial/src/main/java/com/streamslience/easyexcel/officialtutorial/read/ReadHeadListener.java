package com.streamslience.easyexcel.officialtutorial.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 *
 * <h2>行头监听器</h2>
 *
 * <b>注意：</b>
 * <br>{@link AnalysisEventListener#invokeHead(Map, AnalysisContext)}和 {@link AnalysisEventListener#invokeHeadMap(Map, AnalysisContext)}
 * <br>两个方法都可用于解析行头数据，但是两者选其一重写即可，
 * <br>这是应为EacyExcel在解析行头数据时实际上是调用{@link com.alibaba.excel.read.listener.ReadListener#invokeHead(Map, AnalysisContext)}
 * <br>方法，在{@link AnalysisEventListener}中将改方法重写了，重写后改方法仅仅是{@link AnalysisEventListener#invokeHeadMap(Map, AnalysisContext)}方法的包装方法。
 *
 * @author StreamSlience
 * @date 2020-11-02 18:58
 */
@Slf4j
public class ReadHeadListener extends AnalysisEventListener<ReadBO> {

    @Override
    public void invoke(ReadBO data, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一行头数据：{}", JSON.toJSONString(headMap));
    }
}
