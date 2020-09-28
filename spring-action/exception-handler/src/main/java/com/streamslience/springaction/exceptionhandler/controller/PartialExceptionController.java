package com.streamslience.springaction.exceptionhandler.controller;

import com.streamslience.springaction.exceptionhandler.tool.ErrorCode;
import com.streamslience.springaction.exceptionhandler.tool.ErrorResult;
import com.streamslience.springaction.exceptionhandler.tool.ResponseCode;
import com.streamslience.springaction.exceptionhandler.tool.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 局部异常处理演示
 *
 * @author StreamSlience
 * @date 2020-07-21 21:40
 */
@Slf4j
@RestController
@RequestMapping(value = "/partial")
public class PartialExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<ErrorResult> exceptionHandler(IllegalArgumentException e) {
        log.error("局部异常:{}", e.getMessage(), e);
        ErrorResult result = ErrorResult.builder().errorCode(ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION).exception(e).build();
        return new Result.Builder<ErrorResult>().data(result).responseCode(ResponseCode.UN_KNOW_ERROR).build();
    }

    /**
     * 注意： 对于同一种类型的异常不可以同时定义局部异常处理和全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<ErrorResult> exceptionHandler(NullPointerException e) {
        log.error("局部空指针异常:{}", e.getMessage(), e);
        ErrorResult result = ErrorResult.builder().errorCode(ErrorCode.NULLPOINT_EXCEPTION).exception(e).build();
        return new Result.Builder<ErrorResult>().data(result).responseCode(ResponseCode.UN_KNOW_ERROR).build();
    }

    @GetMapping("/{id}")
    public Result<String> get(@PathVariable("id") String id) {
        if ("0".equals(id)) {
            throw new IllegalArgumentException();
        }
        return Result.success("");
    }

    @GetMapping("/add")
    public Result<String> add() {
        throw new NullPointerException();
    }

}
