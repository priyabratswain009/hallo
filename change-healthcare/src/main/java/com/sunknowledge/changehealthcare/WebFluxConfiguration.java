package com.sunknowledge.changehealthcare;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
//@EnableWebFlux
public class WebFluxConfiguration implements WebFluxConfigurer {
	@Bean
	public WebClient getWebClient() {
		return WebClient.builder().build();
	}
}
