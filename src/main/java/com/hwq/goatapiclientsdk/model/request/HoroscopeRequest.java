package com.hwq.goatapiclientsdk.model.request;

import com.hwq.goatapiclientsdk.model.enums.RequestMethodEnum;
import com.hwq.goatapiclientsdk.model.params.HoroscopeParams;
import com.hwq.goatapiclientsdk.model.response.NameResponse;
import com.hwq.goatapiclientsdk.model.response.ResultResponse;
import lombok.experimental.Accessors;

/**
 * @Author: HWQ
 * @Date: 2023年09月17日 08:38
 * @Version: 1.0
 * @Description: 随机情话
 */
@Accessors(chain = true)
public class HoroscopeRequest extends BaseRequest<HoroscopeParams, ResultResponse> {

    @Override
    public String getPath() {
        return "/horoscope";
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