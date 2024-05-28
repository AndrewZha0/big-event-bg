package com.kenmi.bigevent.api.error;

import lombok.Getter;

/**
 * 错误码定义
 */
@Getter
public enum ErrorCodeEnum {
    // 通用异常
    SUCCESS(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.INFO, "0000", "successful"),
    SERVICE_ERROR(ErrorTypeEnum.SYSTEM, ErrorLevelEnum.ERROR, "0001", "service error"),
    NOT_FOUND_ERROR(ErrorTypeEnum.SYSTEM, ErrorLevelEnum.ERROR, "0002", "not found error"),
    ALREADY_EXIST_ERROR(ErrorTypeEnum.SYSTEM, ErrorLevelEnum.ERROR, "0003", "already exist error"),
    UNKNOWN(ErrorTypeEnum.SYSTEM, ErrorLevelEnum.ERROR, "0004", "unknown error"),
    THIRD_PARTY_ERROR(ErrorTypeEnum.SYSTEM, ErrorLevelEnum.ERROR, "0005", "third party error"),
    PARAM_ILLEGAL(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.WARN, "0006", "parameter illegal"),
    PARAM_UNRECOGNIZED(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.WARN, "0007", "unrecognized field"),
    NO_AUTHORIZATION(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.WARN, "0007", "no authorization"),

    // 业务异常
    // 登录异常
    REGISTER_USERNAME_EXIST(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.WARN, "1001", "user already exist"),
    LOGIN_USERNAME_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.WARN, "1002", "user does not exist"),
    LOGIN_PASSWORD_ERROR(ErrorTypeEnum.BUSINESS, ErrorLevelEnum.WARN, "1003", "wrong user name or password"),
    ;
    // FIXME: 修改为合适的系统标识
    private static final String SYSTEM = "001";

    @Getter
    public enum ErrorLevelEnum {
        INFO(1),
        WARN(3),
        ERROR(5),
        ;

        /**
         * -- GETTER --
         * Getter method for property <tt>code</tt>.
         *
         * @return property value of code
         */
        private final int code;

        ErrorLevelEnum(int code) {
            this.code = code;
        }

    }

    @Getter
    public enum ErrorTypeEnum {
        BUSINESS(1),
        SYSTEM(3),
        THIRD_PARTY(5),

        ;

        /**
         * -- GETTER --
         * Getter method for property <tt>code</tt>.
         *
         * @return property value of code
         */
        private final int code;

        ErrorTypeEnum(int code) {
            this.code = code;
        }

    }

    /**
     * -- GETTER --
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    private final ErrorTypeEnum type;
    /**
     * -- GETTER --
     * Getter method for property <tt>level</tt>.
     *
     * @return property value of level
     */
    private final ErrorLevelEnum level;
    /**
     * -- GETTER --
     * Getter method for property <tt>detailCode</tt>.
     *
     * @return property value of detailCode
     */
    private final String detailCode;
    /**
     * -- GETTER --
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    private final String description;

    ErrorCodeEnum(ErrorTypeEnum type, ErrorLevelEnum level, String detailCode, String description) {
        this.type = type;
        this.level = level;
        this.detailCode = detailCode;
        this.description = description;
    }

    public String getSystem() {
        return SYSTEM;
    }

    public String toCode() {
        return String.format("%d%d%s%s", getType().getCode(), getLevel().getCode(), getSystem(), getDetailCode());
    }

}