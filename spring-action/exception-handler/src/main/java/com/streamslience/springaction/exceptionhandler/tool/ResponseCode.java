package com.streamslience.springaction.exceptionhandler.tool;

/**
 * @author StreamSlience
 * @date 2020-07-21 12:17
 */
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
