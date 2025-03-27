package com.fintonic.acceptance.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiFactory {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public ServiceApi serviceApi(final RestTemplate restTemplate) {
    return new ServiceApi(restTemplate);
  }
}
