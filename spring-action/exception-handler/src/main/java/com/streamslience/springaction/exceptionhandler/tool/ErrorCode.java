package com.streamslience.springaction.exceptionhandler.tool;

/**
 * @author StreamSlience
 * @date 2020-07-21 14:40
 */
public enum ErrorCode {

    /**
     *
     */
    UN_KNOW_EXCEPTION("9001", "未知异常"),
    /**
     *
     */
    NULLPOINT_EXCEPTION("9002", "空指针异常"),
    /**
     *
     */
    BIZ_EXCEPTION("9003", "业务异常");

    private String code;
    private String name;

     ErrorCode(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public ErrorCode setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ErrorCode setCode(String code) {
        this.code = code;
        return this;
    }
}
