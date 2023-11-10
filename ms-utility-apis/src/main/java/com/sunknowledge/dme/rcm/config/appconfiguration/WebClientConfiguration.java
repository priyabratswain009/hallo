package com.sunknowledge.dme.rcm.config.appconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration implements WebFluxConfigurer {
    @Bean
    public WebClient getWebClient() {
        return WebClient.builder().build();
    }
}
