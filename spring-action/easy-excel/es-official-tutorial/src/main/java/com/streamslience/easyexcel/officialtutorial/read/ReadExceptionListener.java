package com.streamslience.easyexcel.officialtutorial.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>异常监听器</h2>
 *
 * @author StreamSlience
 * @date 2020-11-02 19:25
 */
@Slf4j
public class ReadExceptionListener extends AnalysisEventListener<ReadBO> {

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
        if ("字符串12".equals(data.getTitle())) {
            throw new RuntimeException("发现字符串12");
        }
        list.add(data);
    }

    /**
     * 所有数据解析完毕都会调用该方法
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有{}条数据解析完毕：{}", list.size(), JSON.toJSONString(list));
        System.out.print("\n");
    }

    /**
     * <b> 注意：</b>
     * <br>异常处理顺序自上而下应该从小到大排列
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        log.error("解析失败{}，继续解析", exception.getMessage());

        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }

        if (exception instanceof RuntimeException) {
            log.error(exception.getMessage());
        }
    }
}
