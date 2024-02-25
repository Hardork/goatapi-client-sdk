package com.hwq.goatapiclientsdk.model.enums;

/**
 * @author HWQ
 * @date 2024/2/25 13:21
 * @description 请求方法枚举类
 */
public enum RequestMethodEnum {
    GET("GET", "GET"),
    POST("POST", "POST");
    private final String text;
    private final String value;
    RequestMethodEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
