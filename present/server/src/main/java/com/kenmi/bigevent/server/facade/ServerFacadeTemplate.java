package com.kenmi.bigevent.server.facade;

import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import com.kenmi.bigevent.api.response.ResultResponse;
import com.kenmi.bigevent.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * 服务器 facade 实现模板
 */
@Slf4j
@Service
public class ServerFacadeTemplate {
    public <T> ResultResponse<T> execute(Supplier<T> command) {
        try {
            T result = command.get();
            return ResultResponse.successResult(result);
        } catch (SystemException e) {
            log.warn("encounter system exception, errorCode={}, errorMessage={}", e.getErrorCode(), e.getMessage());
            return ResultResponse.errorResult(e.getErrorCode().toCode(), e.getMessage());
        } catch (Exception e) {
            log.warn("encounter unknown exception", e);
            return ResultResponse.errorResult(ErrorCodeEnum.UNKNOWN.toCode(), e.getMessage());
        }
    }

    public ResultResponse<Void> execute(Runnable command) {
        return execute(() -> {
            command.run();
            return null;
        });
    }
}