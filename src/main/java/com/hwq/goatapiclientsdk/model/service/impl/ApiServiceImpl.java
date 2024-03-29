package com.hwq.goatapiclientsdk.model.service.impl;


import com.hwq.goatapiclientsdk.client.GoatApiClient;
import com.hwq.goatapiclientsdk.exception.ApiException;
import com.hwq.goatapiclientsdk.model.request.*;
import com.hwq.goatapiclientsdk.model.response.LoveResponse;
import com.hwq.goatapiclientsdk.model.response.PoisonousChickenSoupResponse;
import com.hwq.goatapiclientsdk.model.response.RandomWallpaperResponse;
import com.hwq.goatapiclientsdk.model.response.ResultResponse;
import com.hwq.goatapiclientsdk.model.service.ApiService;
import com.hwq.goatapiclientsdk.model.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @Author: Hwq
 * @Date: 2023年09月17日 08:42
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class ApiServiceImpl extends BaseService implements ApiService {

    @Override
    public PoisonousChickenSoupResponse getPoisonousChickenSoup() throws ApiException {
        PoisonousChickenSoupRequest request = new PoisonousChickenSoupRequest();
        return request(request);
    }

    @Override
    public PoisonousChickenSoupResponse getPoisonousChickenSoup(GoatApiClient goatApiClient) throws ApiException {
        PoisonousChickenSoupRequest request = new PoisonousChickenSoupRequest();
        return request(goatApiClient, request);
    }

    @Override
    public RandomWallpaperResponse getRandomWallpaper(RandomWallpaperRequest request) throws ApiException {
        return request(request);
    }

    @Override
    public RandomWallpaperResponse getRandomWallpaper(GoatApiClient goatApiClient, RandomWallpaperRequest request) throws ApiException {
        return request(goatApiClient, request);
    }

    @Override
    public LoveResponse randomLoveTalk() throws ApiException {
        LoveRequest request = new LoveRequest();
        return request(request);
    }

    @Override
    public LoveResponse randomLoveTalk(GoatApiClient goatApiClient) throws ApiException {
        LoveRequest request = new LoveRequest();
        return request(goatApiClient, request);
    }

    @Override
    public ResultResponse horoscope(HoroscopeRequest request) throws ApiException {
        return request(request);
    }

    @Override
    public ResultResponse horoscope(GoatApiClient goatApiClient, HoroscopeRequest request) throws ApiException {
        return request(goatApiClient, request);
    }

    @Override
    public ResultResponse getIpInfo(GoatApiClient goatApiClient, IpInfoRequest request) throws ApiException {
        return request(goatApiClient, request);
    }

    @Override
    public ResultResponse getIpInfo(IpInfoRequest request) throws ApiException {
        return request(request);
    }

    @Override
    public ResultResponse getWeatherInfo(GoatApiClient goatApiClient, WeatherRequest request) throws ApiException {
        return request(goatApiClient, request);
    }

    @Override
    public ResultResponse getWeatherInfo(WeatherRequest request) throws ApiException {
        return request(request);
    }
}