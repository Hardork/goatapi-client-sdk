package com.hwq.goatapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.hwq.goatapiclientsdk.model.User;
import com.hwq.goatapiclientsdk.utils.SignUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;



/**
 * @Author:HWQ
 * @DateTime:2023/7/16 23:52
 * @Description: 调用第三方接口
 **/
@Data
public class GoatApiClient {
    private String accessKey;
    private String secretKey;
    public GoatApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 模拟开发者调用API
     * @param body
     * @return
     */
    public Map<String, String> getHeaderMap(String body) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("accessKey", accessKey);
        headerMap.put("nonce", RandomUtil.randomNumbers(12));
        headerMap.put("body", body);
        headerMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        headerMap.put("sign", SignUtil.getSign(body, secretKey));
        return headerMap;
    }
}
