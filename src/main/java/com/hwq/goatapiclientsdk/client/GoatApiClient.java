package com.hwq.goatapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.hwq.goatapiclientsdk.model.User;
import com.hwq.goatapiclientsdk.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;



/**
 * @Author:HWQ
 * @DateTime:2023/7/16 23:52
 * @Description: 调用第三方接口
 **/
public class GoatApiClient {
    private String accessKey;
    private String secretKey;
    private final String GATE_WAY_HOST = "http://localhost:8090";
    public GoatApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }
    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        // 去访问携带参数网关
        String res = HttpUtil.get(GATE_WAY_HOST + "/api/name/get", paramMap);
        System.out.println(res);
        return res;
    }

    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String res = HttpUtil.post(GATE_WAY_HOST + "/api/name/post", paramMap);
        System.out.println(res);
        return "Post你的名字是:" + res;
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

    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        String result2 = HttpRequest.post(GATE_WAY_HOST + "/api/name/user").addHeaders(getHeaderMap(json))
                .body(json)
                .execute().body();
        System.out.println(result2);
        return "Post用户的名字是:" + result2;
    }

    public Object invokeByGet(Object obj, String url) {
        String json = JSONUtil.toJsonStr(obj);
        Object res = HttpRequest.get(GATE_WAY_HOST + url).addHeaders(getHeaderMap(json)).body(json).execute().body();
        return res;
    }

    public Object invokeByPost(Object obj, String url) {
        String json = JSONUtil.toJsonStr(obj);
        Object res = HttpRequest.post(GATE_WAY_HOST + url).addHeaders(getHeaderMap(json)).body(json).execute().body();
        return res;
    }
}
