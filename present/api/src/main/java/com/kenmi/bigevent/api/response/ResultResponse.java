package com.kenmi.bigevent.api.response;


import com.kenmi.bigevent.api.error.ErrorCodeEnum;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class ResultResponse<T> {
    private static final String SUCCESS_MSG = "success";
    private String requestId;           //微服务中心相关的请求id
    private String code;                //业务状态码  0-成功  1-失败
    private String message;             //提示信息
    private Boolean success;            //是否成功
    private Map<String, Object> meta;   //一些meta信息，例如需要crsf的token
    private T data;                     //实际数据，一般来说是DTO


    public ResultResponse() {
        this.requestId = UUID.randomUUID().toString();
    }

    public ResultResponse(String reqId) {
        this.requestId = reqId;
    }

    public ResultResponse(String reqId, T payload) {
        this.requestId = reqId;
        this.code = "";
        this.data = payload;
        this.success = true;
    }

    public ResultResponse(String reqId, String code, String msg) {
        this.requestId = reqId;
        this.code = code;
        this.message = msg;
        this.success = true;
    }

    public ResultResponse(String reqId, String code, String msg, T data) {
        this.requestId = reqId;
        this.code = code;
        this.message = msg;
        this.data = data;
        this.success = true;
    }

    public static <T> ResultResponse<T> successResult() {
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setCode(ErrorCodeEnum.SUCCESS.getDetailCode());
        resultResponse.setSuccess(true);
        resultResponse.setMessage(SUCCESS_MSG);
        return resultResponse;
    }

    public static <T> ResultResponse<T> successResult(T data) {
        ResultResponse<T> resultDTO = new ResultResponse<>();
        resultDTO.setCode(ErrorCodeEnum.SUCCESS.getDetailCode());
        resultDTO.setSuccess(true);
        resultDTO.setMessage(SUCCESS_MSG);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultResponse<T> successResult(String reqId, T data) {
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setRequestId(reqId);
        resultResponse.setCode(ErrorCodeEnum.SUCCESS.getDetailCode());
        resultResponse.setSuccess(true);
        resultResponse.setMessage(SUCCESS_MSG);
        resultResponse.setData(data);
        return resultResponse;
    }

    public static <T> ResultResponse<T> errorResult(String message) {
        return errorResult(ErrorCodeEnum.UNKNOWN.getDetailCode(), message);
    }

    public static <T> ResultResponse<T> errorResult(String code, String message) {
        ResultResponse<T> resultDTO = new ResultResponse<>();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setSuccess(false);
        return resultDTO;
    }

    public static <T> ResultResponse<T> errorResult(String reqId, String code, String message) {
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setRequestId(reqId);
        resultResponse.setCode(code);
        resultResponse.setMessage(message);
        resultResponse.setSuccess(false);
        return resultResponse;
    }

    public static <T> ResultResponse<T> errorResult(String reqId, String code, String message, T data) {
        ResultResponse<T> resultResponse = new ResultResponse<>();
        resultResponse.setRequestId(reqId);
        resultResponse.setCode(code);
        resultResponse.setMessage(message);
        resultResponse.setSuccess(false);
        resultResponse.setData(data);
        return resultResponse;
    }

    public static <T> ResultResponse<T> notImplement(T dummy) {
        ResultResponse<T> resultDTO = new ResultResponse<>();
        resultDTO.setCode(ErrorCodeEnum.SERVICE_ERROR.getDetailCode());
        resultDTO.setMessage("暂时还没有实现这个API哟");
        resultDTO.setSuccess(true);
        resultDTO.setData(dummy);
        return resultDTO;
    }

    public static <T> ResultResponse<T> notFound(String message) {
        return errorResult(ErrorCodeEnum.NOT_FOUND_ERROR.getDetailCode(), message);
    }

    public static <T> ResultResponse<T> alreadyExist(String message) {
        return errorResult(ErrorCodeEnum.ALREADY_EXIST_ERROR.getDetailCode(), message);
    }

    public static <T> ResultResponse<T> invalidParam(String param) {
        return errorResult(ErrorCodeEnum.PARAM_ILLEGAL.getDetailCode(), param);
    }
}
