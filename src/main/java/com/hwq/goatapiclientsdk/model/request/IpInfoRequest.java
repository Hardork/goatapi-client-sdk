package com.hwq.goatapiclientsdk.model.request;


import com.hwq.goatapiclientsdk.model.enums.RequestMethodEnum;
import com.hwq.goatapiclientsdk.model.params.IpInfoParams;
import com.hwq.goatapiclientsdk.model.response.NameResponse;
import com.hwq.goatapiclientsdk.model.response.ResultResponse;
import lombok.experimental.Accessors;

/**
 * @Author: HWQ
 * @Date: 2023/09/22 09:04:04
 * @Version: 1.0
 * @Description: 获取ip地址请求
 */
@Accessors(chain = true)
public class IpInfoRequest extends BaseRequest<IpInfoParams, ResultResponse> {

    @Override
    public String getPath() {
        return "/ipInfo";
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