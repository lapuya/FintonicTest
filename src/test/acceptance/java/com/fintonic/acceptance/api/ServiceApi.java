package com.fintonic.acceptance.api;

import java.util.Map;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

public class ServiceApi {

  @Value("${service.host.url}")
  private String serviceUrl;

  private final RestTemplate restTemplate;

  public ServiceApi(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Map<String, String> getStatus() {

    final String url = Strings.concat(serviceUrl, "/actuator/health");

    final ParameterizedTypeReference<Map<String, String>> responseType =
      new ParameterizedTypeReference<>() {};

    RequestEntity<Void> request = RequestEntity.get(url)
      .accept(MediaType.APPLICATION_JSON).build();

    return restTemplate.exchange(request, responseType).getBody();
  }
}
