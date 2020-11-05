package com.streamslience.easyexcel.esdatabaseoperation.controller;

import com.streamslience.easyexcel.esdatabaseoperation.domain.response.Result;
import com.streamslience.easyexcel.esdatabaseoperation.domain.response.StatusEnum;
import com.streamslience.easyexcel.esdatabaseoperation.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 物料前端控制层
 *
 * @author StreamSlience
 * @date 2020-11-04 18:58
 */
@Slf4j
@RestController
@RequestMapping("/good")
public class GoodController {

    @Resource
    private GoodService goodService;

    @RequestMapping(value = "/excel/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<Boolean> imPurchaseOrderByExcelNew(@RequestPart("file") MultipartFile file, @RequestParam(value = "tenantId",required = true) String tenantId) {
        Result result;

        try {
            result = goodService.imPurchaseOrderByExcelNew(file, tenantId);
        } catch (Exception e) {
            log.error("物料Excel导入异常：{}", e.getMessage(), e);
            result = Result.ofThrowable(StatusEnum.FAIL.getErrorCode(), e);
        }

        return result;
    }

}
