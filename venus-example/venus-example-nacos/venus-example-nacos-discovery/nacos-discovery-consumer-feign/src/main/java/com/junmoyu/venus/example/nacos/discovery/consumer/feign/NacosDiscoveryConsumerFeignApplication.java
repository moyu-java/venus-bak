package com.junmoyu.venus.example.nacos.discovery.consumer.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * nacos discovery consumer example with openfeign.
 *
 * @author moyu.jun
 * @date 2022/3/16
 */
@EnableFeignClients
@SpringBootApplication
public class NacosDiscoveryConsumerFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryConsumerFeignApplication.class, args);
    }
}
