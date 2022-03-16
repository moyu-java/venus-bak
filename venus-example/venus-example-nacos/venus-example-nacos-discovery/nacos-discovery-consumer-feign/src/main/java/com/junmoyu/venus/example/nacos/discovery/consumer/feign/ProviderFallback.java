package com.junmoyu.venus.example.nacos.discovery.consumer.feign;

import org.springframework.stereotype.Component;

/**
 * @author moyu.jun
 * @date 2022/3/16
 */
@Component
public class ProviderFallback implements RemoteProviderClient {

    @Override
    public String echo(String message) {
        return "echo error. Please try again later.";
    }
}
