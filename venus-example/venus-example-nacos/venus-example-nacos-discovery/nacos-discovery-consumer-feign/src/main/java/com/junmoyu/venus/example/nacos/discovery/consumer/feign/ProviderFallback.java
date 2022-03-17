package com.junmoyu.venus.example.nacos.discovery.consumer.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 当 nacos-discovery-provider 服务停止，将触发降级策略
 *
 * @author moyu.jun
 * @date 2022/3/16
 */
@Slf4j
@Component
public class ProviderFallback implements RemoteProviderClient {

    @Override
    public String echo(String message) {
        log.error("服务异常，触发降级策略");
        return "The system is busy. Please try again later.";
    }
}
