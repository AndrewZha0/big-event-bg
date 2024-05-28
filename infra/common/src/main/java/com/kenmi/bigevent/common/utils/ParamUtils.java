package com.kenmi.bigevent.common.utils;


import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import com.kenmi.bigevent.common.exception.BusinessException;
import com.kenmi.bigevent.common.exception.SystemException;

import java.util.Collection;
import java.util.Map;

/**
 * 参数检查工具类
 */
public class ParamUtils {

    public static void loginFail(ErrorCodeEnum errorCode) {
        throw new BusinessException(errorCode);
    }

    public static void fail(ErrorCodeEnum errorCode, String message) {
        throw new SystemException(errorCode, message);
    }

    public static void fail(ErrorCodeEnum errorCode) {
        throw new SystemException(errorCode);
    }

    public static void checkTrue(boolean predicate, ErrorCodeEnum errorCode, String message) {
        if (!predicate) {
            fail(errorCode, message);
        }
    }

    public static void checkTrue(boolean predicate, ErrorCodeEnum errorCode) {
        checkTrue(predicate, errorCode, errorCode.getDescription());
    }

    public static void checkTrue(boolean predicate, String message) {
        checkTrue(predicate, ErrorCodeEnum.PARAM_ILLEGAL, message);
    }

    public static void checkIsNull(Object obj, ErrorCodeEnum errorCode, String message) {
        if (obj != null) {
            fail(errorCode, message);
        }
    }

    public static void checkIsNull(Object obj, ErrorCodeEnum errorCode) {
        checkIsNull(obj, errorCode, errorCode.getDescription());
    }

    public static void checkIsNull(Object obj, String fieldName) {
        checkIsNull(obj, ErrorCodeEnum.PARAM_ILLEGAL, fieldName + " must be null");
    }

    public static void checkNotNull(Object obj, ErrorCodeEnum errorCode, String message) {
        if (obj == null) {
            fail(errorCode, message);
        }
    }

    public static void checkNotNull(Object obj, ErrorCodeEnum errorCode) {
        checkNotNull(obj, errorCode, errorCode.getDescription());
    }

    public static void checkNotNull(Object obj, String fieldName) {
        checkNotNull(obj, ErrorCodeEnum.PARAM_ILLEGAL, fieldName + " cannot be null");
    }

    public static void checkNotBlank(String arg, ErrorCodeEnum errorCode, String message) {
        if (arg == null || arg.trim().isEmpty()) {
            fail(errorCode, message);
        }
    }

    public static void checkNotBlank(String arg, ErrorCodeEnum errorCode) {
        checkNotBlank(arg, errorCode, errorCode.getDescription());
    }

    public static void checkNotBlank(String arg, String fieldName) {
        checkNotBlank(arg, ErrorCodeEnum.PARAM_ILLEGAL, fieldName + " cannot be blank");
    }

    public static void checkNotEmpty(Collection<?> arg, ErrorCodeEnum errorCode, String message) {
        if (arg == null || arg.isEmpty()) {
            fail(errorCode, message);
        }
    }

    public static void checkNotEmpty(Collection<?> arg, ErrorCodeEnum errorCode) {
        checkNotEmpty(arg, errorCode, errorCode.getDescription());
    }

    public static void checkNotEmpty(Collection<?> arg, String fieldName) {
        checkNotEmpty(arg, ErrorCodeEnum.PARAM_ILLEGAL, fieldName + " cannot be empty");
    }

    public static void checkNotEmpty(Map<?, ?> arg, ErrorCodeEnum errorCode, String message) {
        if (arg == null || arg.isEmpty()) {
            fail(errorCode, message);
        }
    }

    public static void checkNotEmpty(Map<?, ?> arg, ErrorCodeEnum errorCode) {
        checkNotEmpty(arg, errorCode, errorCode.getDescription());
    }

    public static void checkNotEmpty(Map<?, ?> arg, String fieldName) {
        checkNotEmpty(arg, ErrorCodeEnum.PARAM_ILLEGAL, fieldName + " cannot be empty");
    }

    public static void checkPositive(int num, String fieldName) {
        checkTrue(num > 0, ErrorCodeEnum.PARAM_ILLEGAL, fieldName + " must be positive");
    }

    public static void checkNotNegative(int num, String fieldName) {
        checkTrue(num >= 0, ErrorCodeEnum.PARAM_ILLEGAL, fieldName + " cannot be negative");
    }
}