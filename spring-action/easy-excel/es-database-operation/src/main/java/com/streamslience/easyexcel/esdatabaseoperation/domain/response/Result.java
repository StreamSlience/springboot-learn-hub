package com.streamslience.easyexcel.esdatabaseoperation.domain.response;


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
        this(success, data, statusEnum.getErrorCode(), statusEnum.getErrorMsg());
    }

    public Result(boolean success, R data, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <R> Result<R> ofSuccess(R data) {
        return new Result<>(true, data, StatusEnum.SUCCESS);
    }

    public static <R> Result<R> ofSuccessMsg(String msg) {
        return new Result<R>(true, null, StatusEnum.SUCCESS.getErrorCode(), msg);
    }

    public static <R> Result<R> ofFail(int code, String msg) {
        return new Result<R>(false, null, StatusEnum.FAIL.getErrorCode(), msg);
    }

    public static <R> Result<R> ofThrowable(int code, Throwable throwable) {
        return new Result<R>(false, null, code, throwable.getClass().getName() + ", " + throwable.getMessage());
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
