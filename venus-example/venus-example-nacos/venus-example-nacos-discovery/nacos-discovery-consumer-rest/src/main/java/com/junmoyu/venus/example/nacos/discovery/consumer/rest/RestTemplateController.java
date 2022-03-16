package com.junmoyu.venus.example.nacos.discovery.consumer.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * nacos discovery consumer controller with RestTemplate.
 *
 * @author moyu.jun
 * @date 2022/3/16
 */
@Slf4j
@RestController
@RequestMapping("/nacos/discovery/consumer/rest")
@RequiredArgsConstructor
public class RestTemplateController {

    private final LoadBalancerClient loadBalancerClient;

    private final RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/echo")
    public String echo() {
        // 使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        // LoadBalanceClient 提供负载均衡的功能，并从 Nacos 中根据服务名获取服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-discovery-provider");
        String url = String.format("http://%s:%s/nacos/discovery/provider/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), applicationName);

        log.info("request url: {}", url);
        return restTemplate.getForObject(url, String.class);
    }
}
