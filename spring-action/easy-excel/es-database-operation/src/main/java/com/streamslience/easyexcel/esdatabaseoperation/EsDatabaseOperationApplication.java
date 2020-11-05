package com.streamslience.easyexcel.esdatabaseoperation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EasyExcel 结合数据库 操作
 */
@SpringBootApplication
@MapperScan({"com.streamslience.easyexcel.esdatabaseoperation.dao"})
public class EsDatabaseOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsDatabaseOperationApplication.class, args);
    }

}
