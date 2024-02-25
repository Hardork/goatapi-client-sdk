package com.hwq.goatapiclientsdk.exception;

/**
 * @author HWQ
 * @date 2024/2/25 14:16
 * @description
 */
public class ApiException extends RuntimeException{
    private final int code;

    private final String description;


    public ApiException(String message, int code, String description){
        super(message);
        this.code = code;
        this.description = description;
    }

    public ApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getMessage();
    }

    public ApiException(ErrorCode errorCode,String description){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
