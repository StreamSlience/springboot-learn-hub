# SpringBoot 异常拦截

`SpringBoot`提供两种异常捕获机制

- 局部异常处理：`@Controller + @ExceptionHandler`

- 全局异常处理：`@ControllerAdvice + @ExceptionHandler`

   ​		异常处理主要用到的是`@ExceptionHandler`注解，该注解作用于类的方法上，当此注解中指定的异常类型被抛出时，此方法会被执行。同时根据类上作用的注解，该异常处理方法的作用于是不同的。
   
   ​		当异常处理方法所在的类被`**@Controller**`或**`@RestController`**作用，则此方法仅仅作用于此类(局部异常处理)；反之如果类被`**@ControllerAdvice**`或`**@RestController**`作用，则此方法作用于全局(全局异常处理)。
   
   

在`@ExceptionHandler`注解修饰的异常处理方法中入参可以有以下任意顺序的参数类型：

- `Throwable`、`Exception`等异常对象
- `ServletRequest`、`HttpServletRequest`、`ServletResponse`、`HttpServletResponse`、`HttpSession` 等会话对象
- `org.springframework.web.context.request.WebRequest`
- `java.util.Locale`
- `java.io.InputStream`、`java.io.Reader`、`java.io.OutputStream`、`java.io.Writer`
- `org.springframework.ui.Model`

并且被该注解标注的方法可以有以下的返回值类型可选：

- `ModelAndView`
- `org.springframework.ui.Model`
- `java.util.Map`
- `org.springframework.web.servlet.View`
- `void`
- 普通的bean对象

[ExceptionHandler (Spring Framework 5.2.7.RELEASE API)](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html)



## 局部异常处理：`@Controller + @ExceptionHandler`

```java
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
```

**注意：**对于同一种类型的异常不可以同时定义局部异常处理和全局异常处理

 

## 全局异常处理： `@ControllerAdvice + @ExceptionHandler`

​		在`Spring3.2`中新增了`@ControllerAdvice`注解，可以用于定义`@ExceptionHandler`、`@InitBinder`、`@ModelAttribute`，并应用到所有`@RequestMapping`中。

​		简单的说，进入`Controller`层的错误才会由`@ControllerAdvice`处理，拦截器抛出的错误以及访问错误的情况`@ControllerAdvice`处理不了，有`SpringBoot`默认的异常处理机制处理。

​		在实际开发过程中，现在都是前后端分离模式，因此需要定义同一的返回`JSON`格式

- **请求返回类**

    ```java
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
    ```

    返回的JSON格式大致如下：

    ```json
    {
        "data": {
            "id": null,
            "name": null,
            "age": null
        },
        "code": "0000",
        "msg": "成功"
    }
    ```

- **异常枚举**

    ```java
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
        BIZ_EXCEPTION("9003", "业务异常"),
        /**
         *
         */
        ILLEGAL_ARGUMENT_EXCEPTION("9004", "参数非法异常"),
        ;

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
    ```

- **异常返回类**

    ```java
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
    ```

- **返回标识枚举**

    ```java
    public enum ResponseCode {

        /**
         *
         */
        SUCCESS("0000", "成功"),
        /**
         *
         */
        UN_KNOW_ERROR("1000", "未知异常"),
        /**
         *
         */
        DB_ERROR("1002", "数据库操作异常"),
        /**
         *
         */
        DB_TIMEOUT("1003", "数据库访问超时"),
        /**
         *
         */
        NETWORK_EXP("1004", "网络异常"),
        /**
         *
         */
        EXTERNAL_SERVICE_EXP("1005", "外部服务器异常"),
        /**
         *
         */
        EXTERNAL_SERVICE_TIMEOUT("1006", "外部服务器超时"),
        /**
         *
         */
        API_OVERDUE("1007", "API已过期"),
        /**
         *
         */
        METHOD_UN_IMPL("1008", "当前操作未实现"),
        /**
         *
         */
        JSONP_NOT_SUP("1009", "不支持Jsonp请求"),
        /**
         *
         */
        ILLEGAL_REQ("1010", "非法请求"),
        /**
         *
         */
        INVALID_OP("1011", "无效操作"),
        /**
         *
         */
        DATA_ERR("2000", "数据错误"),
        /**
         *
         */
        DATA_EXIST("2001", "数据已存在"),
        /**
         *
         */
        DATA_NOT_EXIST("2002", "数据不存在"),
        /**
         *
         */
        DATA_FORMAT_ERR("2003", "数据格式错误"),
        /**
         *
         */
        DATA_TYPE_ERR("2004", "数据类型错误"),
        /**
         *
         */
        DATA_REPAT_ERR("2005", "数据重复"),
        /**
         *
         */
        DATA_UN_ACCESS("2006", "数据没有授权"),
        /**
         *
         */
        PARA_ERR("3000", "参数错误"),
        /**
         *
         */
        PARA_MISSING_ERR("3002", "缺少参数"),
        /**
         *
         */
        PARA_NIL("3003", "参数不能为空值"),
        /**
         *
         */
        PARA_FORMAT_ERR("3004", "参数格式错误"),
        /**
         *
         */
        PARA_OUT_RANGE("3005", "参数值超出允许范围"),
        /**
         *
         */
        TOKEN_INVALID("3006", "令牌无效"),
        /**
         *
         */
        TOKEN_IS_USE("3007", "令牌已使用"),
        /**
         *
         */
        TOKEN_TIMEOUT("3008", "令牌过期"),
        /**
         *
         */
        SIGNATURE_INVALID("3009", "签名无效"),
        /**
         *
         */
        TIME_OUT_RANGE("3010", "时间戳超出允许范围"),
        /**
         *
         */
        CHR_ILLEGAL("3011", "存在非法字符"),
        /**
         *
         */
        PARA_OUT_LEN("3012", "参数值长度超过限制"),
        /**
         *
         */
        HAS_SUBTLE("3013", "存在有敏感词"),
        /**
         *
         */
        SIGNATURE_ERROR("3014", "签名错误"),
        /**
         *
         */
        CAPTCHA_ERROR("3015", "验证码错误"),
        /**
         *
         */
        ACCESS_DENIED("4000", "无权限"),
        /**
         *
         */
        NOT_LOG("4002", "未登录"),
        /**
         *
         */
        IP_LIMIT("4003", "IP限制"),
        /**
         *
         */
        API_ACCESS_DENIED("4004", "API未授权"),
        ;

        private String code;
        private String name;

        private ResponseCode(String code, String name) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public ResponseCode setName(String name) {
            this.name = name;
            return this;
        }

        public String getCode() {
            return code;
        }

        public ResponseCode setCode(String code) {
            this.code = code;
            return this;
        }
    }
    ```

- **全局异常处理类**

    ```java
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
    ```

- **控制层**

    ```java
    @RestController
    @RequestMapping("/user")
    public class UserController {

        @GetMapping(value = "/{id}")
        public Result<UserEntity> get(@PathVariable("id") String id) {
            if ("0".equals(id)) {
                throw new NullPointerException("查询异常");
            }
            return Result.success(new UserEntity());
        }

        @GetMapping(value = "/add")
        public Result<Boolean> add() {
            throw new BizException("新增用户失败");
        }

    }
    ```



[Spring Boot干货系列：（十三）Spring Boot全局异常处理整理](http://tengj.top/2018/05/16/springboot13/)

[SpringBoot优雅的全局异常处理](https://www.cnblogs.com/xuwujing/p/10933082.html)

[ExceptionHandler (Spring Framework 5.2.7.RELEASE API)](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html)