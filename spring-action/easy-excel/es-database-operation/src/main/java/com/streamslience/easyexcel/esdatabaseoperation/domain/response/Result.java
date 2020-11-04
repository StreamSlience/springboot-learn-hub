package com.streamslience.easyexcel.esdatabaseoperation.domain.response;

import java.util.Date;

/**
 * @param <R>
 */
public class Result<R> {

    private boolean success;
    private int code;
    private String msg;
    private R data;

    public Result() {
    }

    public Result(boolean success, R data, StatusEnum statusEnum) {
        this(success, statusEnum.getErrorCode(), statusEnum.getErrorMsg(), data);
    }

    public Result(boolean success, int code, String msg, R data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <R> Result<R> ofSuccess(R data) {
        return new Result<>(true,data,StatusEnum.SUCCESS);
    }

    public static <R> Result<R> ofSuccessMsg(String msg) {
        return new Result<R>()
                .setSuccess(true)
                .setCode(StatusEnum.SUCCESS.getErrorCode())
                .setMsg(msg);
    }

    public static <R> Result<R> ofFail(int code, String msg) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(StatusEnum.FAIL.getErrorCode());
        result.setMsg(msg);
        return result;
    }

    public static <R> Result<R> ofThrowable(int code, Throwable throwable) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<R> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<R> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<R> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R getData() {
        return data;
    }

    public Result<R> setData(R data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
