package com.kenmi.bigevent.common.exception;


import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import lombok.Getter;

import java.io.Serial;

/**
 * 系统异常定义
 *
 * @author andrew
 */
@Getter
public class SystemException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2056345976913968871L;
    /**
     * -- GETTER --
     * Getter method for property <tt>errorCode</tt>.
     */
    private final ErrorCodeEnum errorCode;

    public SystemException(ErrorCodeEnum errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
    public SystemException(ErrorCodeEnum errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public SystemException(ErrorCodeEnum errorCode, Throwable t) {
        super(t);
        this.errorCode = errorCode;
    }

}