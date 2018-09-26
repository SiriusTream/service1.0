package com.cntest.su.feign.config;

import org.springframework.boot.autoconfigure.web.WebMvcRegistrations;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.cntest.su.feign.handle.FeignWebMvcRegistrations;

/**
 * 组件配置。
 */
@Configuration
public class FeignAutoConfiguration {
  /**
   * 配置REST组件。
   * 
   * @return 返回REST组件。
   */
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  /**
   * 配置WebMvcRegistrations组件。
   * 
   * @return 返回WebMvcRegistrations组件。
   */
  @Bean
  public WebMvcRegistrations feignWebRegistrations() {
    return new FeignWebMvcRegistrations();
  }
}
