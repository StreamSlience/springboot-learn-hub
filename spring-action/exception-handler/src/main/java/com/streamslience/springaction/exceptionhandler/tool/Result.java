package com.streamslience.springaction.exceptionhandler.tool;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author StreamSlience
 * @date 2020-07-21 11:24
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 109447904817524981L;

    private T data;
    private String code;
    private String msg;

    private Result() {
    }

    private Result(T data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    private Result(T data, ResponseCode responseCode) {
        this.data = data;
        this.code = responseCode.getCode();
        this.msg = responseCode.getName();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, ResponseCode.SUCCESS);
    }

    public static <T> Result<T> failure() {
        return new Result<>(null, ResponseCode.UN_KNOW_ERROR);
    }

    public static <T> Result<T> failure(T data) {
        Objects.requireNonNull(data);
        return new Result<>(data, ResponseCode.UN_KNOW_ERROR);
    }

    public static class Builder<T> {
        private T data;
        private String code;
        private String msg;
        private ResponseCode responseCode = ResponseCode.SUCCESS;

        public Builder() {
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<T> responseCode(ResponseCode responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Result<T> build() {
            T data = this.data;
            ResponseCode responseCode = this.responseCode;
            String code = Objects.equals(null, this.code) ? responseCode.getCode() : this.code;
            String msg = Objects.equals(null, this.msg) ? responseCode.getName() : this.msg;

            return new Result<T>(data, code, msg);
        }

        public Result<T> success() {
            return new Result<>(this.data, ResponseCode.SUCCESS);
        }

        public Result<T> success(T data) {
            Objects.requireNonNull(data);
            return new Result<>(data, ResponseCode.SUCCESS);
        }

        public Result<T> failure() {
            return new Result<>(null, ResponseCode.UN_KNOW_ERROR);
        }
    }
}
