package com.streamslience.springaction.exceptionhandler.exception.handler;

import com.streamslience.springaction.exceptionhandler.exception.BizException;
import com.streamslience.springaction.exceptionhandler.tool.ErrorCode;
import com.streamslience.springaction.exceptionhandler.tool.ErrorResult;
import com.streamslience.springaction.exceptionhandler.tool.ResponseCode;
import com.streamslience.springaction.exceptionhandler.tool.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 *
 * @author StreamSlience
 * @date 2020-07-21 13:47
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param request
     * @param e BizException
     * @return Result<ErrorResult>
     */
    @ExceptionHandler(BizException.class)
    public Result<ErrorResult> exceptionHandler(HttpServletRequest request, BizException e) {
        log.error("业务异常:{}", e.getMessage(),e);
        ErrorResult result = ErrorResult.builder().errorCode(ErrorCode.BIZ_EXCEPTION).exception(e).build();
        return new Result.Builder<ErrorResult>().data(result).responseCode(ResponseCode.UN_KNOW_ERROR).build();
    }


    /**
     * 空指针异常处理
     *
     * @param request
     * @param e       NullPointerException
     * @return Result<ErrorResult>
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<ErrorResult> exceptionHandler(HttpServletRequest request, NullPointerException e) {
        log.error("空指针异常:{}", e.getMessage(),e);
        ErrorResult result = ErrorResult.builder().errorCode(ErrorCode.NULLPOINT_EXCEPTION).exception(e).build();
        return new Result.Builder<ErrorResult>().data(result).responseCode(ResponseCode.UN_KNOW_ERROR).build();
    }

    /**
     * 其他异常处理
     *
     * @param request
     * @param e       Exception
     * @return Result<ErrorResult>
     */
    @ExceptionHandler(Exception.class)
    public Result<ErrorResult> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("未知异常:{}", e.getMessage(), e);
        ErrorResult result = ErrorResult.builder().errorCode(ErrorCode.UN_KNOW_EXCEPTION).exception(e).build();
        return new Result.Builder<ErrorResult>().data(result).responseCode(ResponseCode.UN_KNOW_ERROR).build();
    }

}
