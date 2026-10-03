package com.payment.payment_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient bankClient(RestClient.Builder builder, @Value("${bank.url}") String bankUrl) {
        return builder.baseUrl(bankUrl).build();
    }
}
