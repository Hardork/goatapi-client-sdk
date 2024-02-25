package com.hwq.goatapiclientsdk.model.request;


import com.hwq.goatapiclientsdk.model.enums.RequestMethodEnum;
import com.hwq.goatapiclientsdk.model.params.NameParams;
import com.hwq.goatapiclientsdk.model.response.NameResponse;
import lombok.experimental.Accessors;

/**
 * @Author: HWQ
 * @Date: 2023年09月17日 08:38
 * @Version: 1.0
 * @Description:
 */
@Accessors(chain = true)
public class NameRequest extends BaseRequest<NameParams, NameResponse> {

    @Override
    public String getPath() {
        return "/name";
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link NameResponse}>
     */
    @Override
    public Class<NameResponse> getResponseClass() {
        return NameResponse.class;
    }


    @Override
    public String getMethod() {
        return RequestMethodEnum.GET.getValue();
    }
}