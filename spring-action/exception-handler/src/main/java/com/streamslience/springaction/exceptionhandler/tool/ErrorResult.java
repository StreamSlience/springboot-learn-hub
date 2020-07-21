package com.streamslience.springaction.exceptionhandler.tool;

import lombok.Data;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author StreamSlience
 * @date 2020-07-21 14:16
 */
@Data
public class ErrorResult {

    private String code;

    private String name;

    private String message;

    private String description;

    private ErrorResult(String code, String name, String message,String description) {
        this.code = code;
        this.name = name;
        this.message = message;
        this.setDescription(description);
    }

    public ErrorResult(ErrorCode errorCode, String message) {
        this.code = errorCode.getCode();
        this.name = errorCode.getName();
        this.message = message;
    }

    public static ErrorResultBuilder builder() {
        return new ErrorResultBuilder();
    }

    public ErrorResultBuilder toBuilder() {
        return new ErrorResultBuilder().code(this.code).name(this.name).message(this.message);
    }

    public static class ErrorResultBuilder {
        private ErrorCode errorCode;
        private Exception exception;
        private String code;
        private String name;
        private String message;
        private String description;

        ErrorResultBuilder() {
        }

        public ErrorResultBuilder errorCode(ErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorResultBuilder exception(Exception e) {
            this.exception = e;
            return this;
        }

        public ErrorResultBuilder code(String code) {
            this.code = code;
            return this;
        }

        public ErrorResultBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ErrorResultBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResultBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ErrorResult build() {
            String message;
            String name;
            String code;
            String description;
            ErrorCode errorCode = this.errorCode;
            Exception exception = this.exception;

            if (Objects.equals(null, exception)) {
                message = this.message;
                name = this.name;
                description = this.description;
            } else {
                message = exception.getMessage();
                name = exception.getClass().getSimpleName();
                description = Arrays.toString(exception.getStackTrace());
            }

            if (Objects.equals(null, errorCode)) {
                code = this.code;
            } else {
                code = errorCode.getCode();
            }

            return new ErrorResult(code, name, message,description);
        }
    }
}
