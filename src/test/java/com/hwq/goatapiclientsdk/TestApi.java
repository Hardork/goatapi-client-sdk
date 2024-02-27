package com.hwq.goatapiclientsdk;

import com.hwq.goatapiclientsdk.client.GoatApiClient;
import com.hwq.goatapiclientsdk.model.response.PoisonousChickenSoupResponse;
import com.hwq.goatapiclientsdk.model.service.ApiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author HWQ
 * @date 2024/2/25 21:26
 * @description
 */
@SpringBootTest
public class TestApi {

    @Resource
    private ApiService apiService;
    @Test
    public void testApi() {
        PoisonousChickenSoupResponse poisonousChickenSoup = apiService.getPoisonousChickenSoup(new GoatApiClient("123", "456"));
        System.out.println(poisonousChickenSoup);
    }
}
