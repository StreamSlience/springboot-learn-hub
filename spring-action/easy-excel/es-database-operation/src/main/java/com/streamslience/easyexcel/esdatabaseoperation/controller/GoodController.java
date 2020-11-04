package com.streamslience.easyexcel.esdatabaseoperation.controller;

import com.streamslience.easyexcel.esdatabaseoperation.domain.response.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物料前端控制器
 *
 * @author StreamSlience
 * @date 2020-11-04 18:58
 */
@RestController
@RequestMapping("good")
public class GoodController {


    @RequestMapping(value = "/excel/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<Boolean> imPurchaseOrderByExcelNew(@RequestPart("file") MultipartFile file) {


        Result<Boolean> result = new Result<>();
        result.setData(true);


        return null;
    }

}
