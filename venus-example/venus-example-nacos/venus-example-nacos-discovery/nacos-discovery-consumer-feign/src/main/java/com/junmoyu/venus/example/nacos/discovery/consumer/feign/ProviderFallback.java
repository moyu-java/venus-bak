package com.junmoyu.venus.example.nacos.discovery.consumer.feign;

import org.springframework.stereotype.Component;

/**
 * 当 nacos-discovery-provider 服务停止，将触发降级策略
 *
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
