package com.streamslience.easyexcel.esdatabaseoperation.service;

import com.alibaba.excel.EasyExcel;
import com.streamslience.easyexcel.esdatabaseoperation.dao.IGoodDao;
import com.streamslience.easyexcel.esdatabaseoperation.domain.bo.ExcelImGoodBO;
import com.streamslience.easyexcel.esdatabaseoperation.domain.response.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 物料业务逻辑层
 *
 * @author StreamSlience
 * @date 2020-11-05 09:43
 */
@Service
public class GoodService {

    @Resource
    private IGoodDao iGoodDao;

    /**
     * @param file
     * @return
     */
    public Result<Boolean> imPurchaseOrderByExcelNew(@RequestPart("file") MultipartFile file, String tenantId) throws IOException {
        Result result = Result.ofSuccess(true);

        GoodReadListener goodReadListener = new GoodReadListener(100, tenantId, iGoodDao);
        //如果下面这种方法用不了，检查以下Maven应用的版本应该在2.0以上
        EasyExcel.read(file.getInputStream(), ExcelImGoodBO.class, goodReadListener).sheet().doRead();

        return result;
    }

}
