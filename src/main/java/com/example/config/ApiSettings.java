package com.example.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api-settings")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiSettings {
    private int connectionTimeOut;
    private int readTimeOut;

    private MyBusinessService myBusinessService;

    @Getter @Setter
    public static class MyBusinessService {
        private String endPoint;
    }

}
