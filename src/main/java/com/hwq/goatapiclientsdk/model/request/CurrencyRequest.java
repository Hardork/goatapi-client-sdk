package com.hwq.goatapiclientsdk.model.request;

import com.hwq.goatapiclientsdk.model.response.ResultResponse;
import lombok.experimental.Accessors;

/**
 * @Author: HWQ
 * @Date: 2023年09月17日 13:59
 * @Version: 1.0
 * @Description:
 */
@Accessors(chain = true)
public class CurrencyRequest extends BaseRequest<Object, ResultResponse> {
    private String method;
    private String path;

    /**
     * get方法
     *
     * @return {@link String}
     */
    @Override
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取路径
     *
     * @return {@link String}
     */
    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link ResultResponse}>
     */
    @Override
    public Class<ResultResponse> getResponseClass() {
        return ResultResponse.class;
    }
}