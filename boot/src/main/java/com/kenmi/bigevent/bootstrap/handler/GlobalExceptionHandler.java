package com.kenmi.bigevent.bootstrap.handler;

import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import com.kenmi.bigevent.api.response.ResultResponse;
import com.kenmi.bigevent.common.exception.BusinessException;
import com.kenmi.bigevent.common.exception.SystemException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author lizhenhua
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResultResponse<Void> handleException(Exception e) {
        log.warn("encounter unknown Exception: {}", e.getMessage(), e);

        // 系统异常
        if (e instanceof SystemException) {
            return ResultResponse.errorResult(((SystemException) e).getErrorCode().getDetailCode(), e.getMessage());
        }

        // 业务异常
        if (e instanceof BusinessException) {
            return ResultResponse.errorResult(((BusinessException) e).getCode(), e.getMessage());
        }

        if (e instanceof MethodArgumentNotValidException) {
            // 参数问题调整返回报错信息
            String msg = "参数有误。";
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                msg += " " +allErrors.stream().map(error -> {
                                    FieldError fieldError = (FieldError) error;
                                    return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                                }).collect(Collectors.joining(", "));
            }
            return ResultResponse.errorResult(ErrorCodeEnum.PARAM_ILLEGAL.getDescription(), msg);
        }
        //方法中入参参数名校验
        if (e instanceof HttpMessageNotReadableException httpMessageNotReadableException) {
            String message = httpMessageNotReadableException.getMessage();
            return ResultResponse.errorResult(ErrorCodeEnum.PARAM_UNRECOGNIZED.toCode(), ErrorCodeEnum.PARAM_UNRECOGNIZED.getDescription() + ":" + message);
        }
        //方法中参数注解校验
        if (e instanceof ConstraintViolationException constraintViolationException) {
            String message = constraintViolationException.getMessage();
            return ResultResponse.errorResult(ErrorCodeEnum.PARAM_ILLEGAL.toCode(), message);
        }
        return ResultResponse.errorResult(ErrorCodeEnum.UNKNOWN.getDetailCode(), e.getMessage());
    }
}