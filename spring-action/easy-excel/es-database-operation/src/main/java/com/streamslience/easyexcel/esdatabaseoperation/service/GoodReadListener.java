package com.streamslience.easyexcel.esdatabaseoperation.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.streamslience.easyexcel.esdatabaseoperation.dao.IGoodDao;
import com.streamslience.easyexcel.esdatabaseoperation.domain.UnitEnum;
import com.streamslience.easyexcel.esdatabaseoperation.domain.bo.ExcelImGoodBO;
import com.streamslience.easyexcel.esdatabaseoperation.domain.entity.GoodEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author StreamSlience
 * @date 2020-11-05 10:05
 */
@Slf4j
public class GoodReadListener extends AnalysisEventListener<ExcelImGoodBO> {

    private Integer batchNumber = 100;

    private List<GoodEntity> goodEntities = new ArrayList<>();

    private IGoodDao iGoodDao;

    private String tenantId;

    public GoodReadListener(IGoodDao iGoodDao) {
        this.iGoodDao = iGoodDao;
    }

    public GoodReadListener(Integer batchNumber, String tenantId, IGoodDao iGoodDao) {
        this.batchNumber = batchNumber;
        this.tenantId = tenantId;
        this.iGoodDao = iGoodDao;
    }

    @Override
    public void invoke(ExcelImGoodBO excelImGoodBO, AnalysisContext analysisContext) {
        //数据转换
        goodEntities.add(convert(excelImGoodBO, tenantId));

        //数据库写入
        if (goodEntities.size() > batchNumber) {
            goodEntities.forEach(iGoodDao::insert);
            goodEntities.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //数据库写入
        goodEntities.forEach(iGoodDao::insert);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        log.error("Excel解析异常:{},继续解析", exception.getMessage(), exception);

        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }
    }

    private GoodEntity convert(ExcelImGoodBO excelImGoodBO, String tenantId) {

        Objects.requireNonNull(excelImGoodBO);
        Objects.requireNonNull(tenantId);

        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setGoodCode(excelImGoodBO.getGoodCode());
        goodEntity.setGoodDesc(excelImGoodBO.getGoodDesc());
        goodEntity.setGoodName(excelImGoodBO.getGoodName());
        goodEntity.setGoodUnit(UnitEnum.getEnumByName(Optional.ofNullable(excelImGoodBO.getGoodUnit()).orElse(UnitEnum.none.getName())).getId());
        goodEntity.setTenantId(tenantId);

        return goodEntity;
    }
}
