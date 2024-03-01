package com.hwq.goatapiclientsdk.model.request;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwq.goatapiclientsdk.model.enums.RequestMethodEnum;
import com.hwq.goatapiclientsdk.model.params.ChatAIParams;
import com.hwq.goatapiclientsdk.model.params.IpInfoParams;
import com.hwq.goatapiclientsdk.model.response.NameResponse;
import com.hwq.goatapiclientsdk.model.response.ResultResponse;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HWQ
 * @date 2024/2/28 11:54
 * @description
 */

@Accessors(chain = true)
public class ChatAIRequest extends BaseRequest<ChatAIParams, ResultResponse> {

    @Override
    public String getPath() {
        return "/chatAi";
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link NameResponse}>
     */
    @Override
    public Class<ResultResponse> getResponseClass() {
        return ResultResponse.class;
    }


    @Override
    public String getMethod() {
        return RequestMethodEnum.GET.getValue();
    }
}