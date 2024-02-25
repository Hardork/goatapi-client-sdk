package com.hwq.goatapiclientsdk;
import com.hwq.goatapiclientsdk.client.GoatApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:HWQ
 * @DateTime:2023/7/17 10:51
 * @Description:
 **/
@Configuration
@ConfigurationProperties(prefix = "goat.api")
@Data
@ComponentScan
public class GoatApiClientConfig {
    private String accessKey;
    private String secretKey;
    @Bean
    public GoatApiClient goatApiClient(){
        return new GoatApiClient(accessKey, secretKey);
    }
}
