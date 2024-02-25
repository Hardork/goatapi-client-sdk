package com.hwq.goatapiclientsdk.model.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: HWQ
 * @Date: 2023/09/22 10:11:04
 * @Version: 1.0
 * @Description: 获取天气请求参数
 */
@Accessors(chain = true)
@Data
public class WeatherParams implements Serializable {
    private static final long serialVersionUID = 3815188540434269370L;
    private String ip;
    private String city;
    private String type;
}