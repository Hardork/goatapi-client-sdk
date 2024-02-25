package com.hwq.goatapiclientsdk.model.request;


import com.hwq.goatapiclientsdk.model.enums.RequestMethodEnum;
import com.hwq.goatapiclientsdk.model.response.LoveResponse;
import com.hwq.goatapiclientsdk.model.response.NameResponse;
import lombok.experimental.Accessors;

/**
 * @Author: HWQ
 * @Date: 2023年09月17日 08:38
 * @Version: 1.0
 * @Description: 随机情话
 */
@Accessors(chain = true)
public class LoveRequest extends BaseRequest<String, LoveResponse> {

    @Override
    public String getPath() {
        return "/loveTalk";
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link NameResponse}>
     */
    @Override
    public Class<LoveResponse> getResponseClass() {
        return LoveResponse.class;
    }


    @Override
    public String getMethod() {
        return RequestMethodEnum.GET.getValue();
    }
}