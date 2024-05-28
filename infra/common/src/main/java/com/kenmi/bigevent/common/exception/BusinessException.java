package com.kenmi.bigevent.common.exception;


import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import lombok.Getter;

import java.io.Serial;

/**
 * 通用业务异常
 *
 * @author andrew
 */
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4488062314627147640L;
    /**
     * 异常结果码
     * -- GETTER --
     * Getter method for property <tt>code</tt>.
     */
    @Getter
    private String code = "error";

    /**
     * 异常结果信息
     */
    private final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(ErrorCodeEnum errorEnum) {
        this.code = errorEnum.getDetailCode();
        this.message = errorEnum.getDescription();
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    @Override
    public String getMessage() {
        return message;
    }
}