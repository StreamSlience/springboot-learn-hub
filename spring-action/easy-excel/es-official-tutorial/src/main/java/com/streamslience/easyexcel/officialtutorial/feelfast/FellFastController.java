package com.streamslience.easyexcel.officialtutorial.feelfast;

import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Excel上传下载
 *
 * @author StreamSlience
 * @date 2020-11-02 12:22
 */
@RestController("excel")
public class FellFastController {

    /**
     * Excel下载
     */
    @ApiOperation(value = "Excel下载")
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=demo.xlsx");
        EasyExcel.write(response.getOutputStream(), FeelFastBO.class).sheet("模板").doWrite(new ArrayList());

    }

    /**
     * Excel上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestPart("file") MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), FeelFastBO.class, new FeelFastListener()).sheet().doRead();
        return "success";
    }


}
