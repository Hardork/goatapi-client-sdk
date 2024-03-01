package com.hwq.goatapiclientsdk.model.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.hwq.goatapiclientsdk.client.GoatApiClient;
import com.hwq.goatapiclientsdk.exception.ApiException;
import com.hwq.goatapiclientsdk.exception.ErrorCode;
import com.hwq.goatapiclientsdk.exception.ErrorResponse;
import com.hwq.goatapiclientsdk.model.request.BaseRequest;
import com.hwq.goatapiclientsdk.model.response.ResultResponse;
import com.hwq.goatapiclientsdk.utils.SignUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;


import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Hwq
 * @Date: 2023年09月19日 11:03
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Data
public abstract class BaseService implements ApiService {
    private GoatApiClient goatApiClient;
    /**
     * 网关HOST
     */
    private String gatewayHost = "http://localhost:8090/api";

    /**
     * 检查配置
     *
     * @param goatApiClient goat api客户端
     * @throws ApiException 业务异常
     */
    public void checkConfig(GoatApiClient goatApiClient) throws ApiException {
        if (goatApiClient == null && this.getGoatApiClient() == null) {
            throw new ApiException(ErrorCode.NO_AUTH_ERROR, "请先配置密钥AccessKey/SecretKey");
        }
        if (goatApiClient != null && !StrUtil.hasBlank(goatApiClient.getAccessKey(), goatApiClient.getSecretKey())) {
            this.setGoatApiClient(goatApiClient);
        }
    }

    /**
     * 执行请求
     *
     * @param request 请求
     * @return {@link HttpResponse}
     * @throws ApiException 业务异常
     */
    private <O, T extends ResultResponse> HttpResponse doRequest(BaseRequest<O, T> request) throws ApiException {
        try (HttpResponse httpResponse = getHttpRequestByRequestMethod(request).execute()) {
            return httpResponse;
        } catch (Exception e) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    /**
     * 通过请求方法获取http响应
     *
     * @param request 要求
     * @return {@link HttpResponse}
     * @throws ApiException 业务异常
     */
    private <O, T extends ResultResponse> HttpRequest getHttpRequestByRequestMethod(BaseRequest<O, T> request) throws ApiException {
        if (ObjectUtils.isEmpty(request)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求参数错误");
        }
        // 获取请求路径
        String path = request.getPath().trim();
        // 获取请求方法
        String method = request.getMethod().trim().toUpperCase();

        if (ObjectUtils.isEmpty(method)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求方法不存在");
        }
        if (StrUtil.isBlank(path)) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, "请求路径不存在");
        }

        if (path.startsWith(gatewayHost)) {
            path = path.substring(gatewayHost.length());
        }
        log.info("请求方法：{}，请求路径：{}，请求参数：{}", method, path, request.getRequestParams());
        HttpRequest httpRequest;
        switch (method) {
            case "GET": { // GET请求
                httpRequest = HttpRequest.get(splicingGetRequest(request, path));
                break;
            }
            case "POST": { // POST请求
                httpRequest = HttpRequest.post(gatewayHost + path);
                break;
            }
            default: {
                throw new ApiException(ErrorCode.OPERATION_ERROR, "不支持该请求");
            }
        }
        return httpRequest.addHeaders(getHeaders(JSONUtil.toJsonStr(request), goatApiClient)).body(JSONUtil.toJsonStr(request.getRequestParams()));
    }

    /**
     * 获取响应数据
     *
     * @param request 要求
     * @return {@link T}
     * @throws ApiException 业务异常
     */
    public <O, T extends ResultResponse> T res(BaseRequest<O, T> request) throws ApiException {
        if (goatApiClient == null || StrUtil.hasBlank(goatApiClient.getAccessKey(), goatApiClient.getSecretKey())) {
            throw new ApiException(ErrorCode.NO_AUTH_ERROR, "请先配置密钥AccessKey/SecretKey");
        }
        T rsp;
        try {
            Class<T> clazz = request.getResponseClass();
            rsp = clazz.newInstance();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        HttpResponse httpResponse = doRequest(request);
        String body = httpResponse.body();
        Map<String, Object> data = new HashMap<>();
        if (httpResponse.getStatus() != 200) {
            ErrorResponse errorResponse = JSONUtil.toBean(body, ErrorResponse.class);
            data.put("errorMessage", errorResponse.getMessage());
            data.put("code", errorResponse.getCode());
        } else {
            try {
                // 尝试解析为JSON对象
                data = new Gson().fromJson(body, new TypeToken<Map<String, Object>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                // 解析失败，将body作为普通字符串处理
                data.put("value", body);
            }
        }
        rsp.setData(data);
        return rsp;
    }

    /**
     * 拼接Get请求
     *
     * @param request 要求
     * @param path    路径
     * @return {@link String}
     */
    private <O, T extends ResultResponse> String splicingGetRequest(BaseRequest<O, T> request, String path) {
        StringBuilder urlBuilder = new StringBuilder(gatewayHost);
        // urlBuilder最后是/结尾且path以/开头的情况下，去掉urlBuilder结尾的/
        if (urlBuilder.toString().endsWith("/") && path.startsWith("/")) {
            urlBuilder.setLength(urlBuilder.length() - 1);
        }
        urlBuilder.append(path);
        if (!request.getRequestParams().isEmpty()) {
            urlBuilder.append("?");
            Map<String, Object> requestParams = request.getRequestParams();
            Map<String, Object> params = (Map<String, Object>)requestParams.get("members");
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Map<String, Object> valueMap = (Map<String, Object>)entry.getValue();
                urlBuilder.append(key).append("=").append(valueMap.get("value").toString()).append("&");

            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }
        log.info("GET请求路径：{}", urlBuilder);
        return urlBuilder.toString();
    }


    /**
     * 获取请求头
     *
     * @param body        请求体
     * @param goatApiClient goat api客户端
     * @return {@link Map}<{@link String}, {@link String}>
     */
    private Map<String, String> getHeaders(String body, GoatApiClient goatApiClient) {
        Map<String, String> hashMap = new HashMap<>(4);
        hashMap.put("accessKey", goatApiClient.getAccessKey());
        String encodedBody = SecureUtil.md5(body);
        hashMap.put("body", encodedBody);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtil.getSign(encodedBody, goatApiClient.getSecretKey()));
        return hashMap;
    }

    @Override
    public <O, T extends ResultResponse> T request(BaseRequest<O, T> request) throws ApiException {
        try {
            return res(request);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    @Override
    public <O, T extends ResultResponse> T request(GoatApiClient goatApiClient, BaseRequest<O, T> request) throws ApiException {
        checkConfig(goatApiClient);
        return request(request);
    }
}