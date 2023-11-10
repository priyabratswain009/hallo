package com.sunknowledge.changehealthcare;

import java.time.Duration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataSourceConfiguration {
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String jdbcURL;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	
	  @Bean public DataSource createDataSource() { DriverManagerDataSource ds = new
	  DriverManagerDataSource(); ds.setUrl(jdbcURL);
	  ds.setDriverClassName(driverClassName); ds.setUsername(username);
	  ds.setPassword(password);
	  
	  return ds; }
	 

//	@Bean
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
	        RestTemplate restTemplate= restTemplateBuilder.setConnectTimeout(Duration.ofMillis(15000)).setReadTimeout(Duration.ofMillis(15000)).build();
	        return restTemplate;

	}
}
