package com.hwq.goatapiclientsdk.model.request;


import com.hwq.goatapiclientsdk.model.enums.RequestMethodEnum;
import com.hwq.goatapiclientsdk.model.params.RandomWallpaperParams;
import com.hwq.goatapiclientsdk.model.response.RandomWallpaperResponse;
import com.hwq.goatapiclientsdk.model.response.ResultResponse;
import lombok.experimental.Accessors;

/**
 * @Author: HWQ
 * @Date: 2023年09月17日 08:38
 * @Version: 1.0
 * @Description:
 */
@Accessors(chain = true)
public class RandomWallpaperRequest extends BaseRequest<RandomWallpaperParams, RandomWallpaperResponse> {
    @Override
    public String getPath() {
        return "/randomWallpaper";
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link ResultResponse}>
     */
    @Override
    public Class<RandomWallpaperResponse> getResponseClass() {
        return RandomWallpaperResponse.class;
    }


    @Override
    public String getMethod() {
        return RequestMethodEnum.GET.getValue();
    }
}