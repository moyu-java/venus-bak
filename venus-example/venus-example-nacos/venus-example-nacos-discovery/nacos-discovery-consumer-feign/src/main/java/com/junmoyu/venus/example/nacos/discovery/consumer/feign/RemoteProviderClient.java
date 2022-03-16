package com.junmoyu.venus.example.nacos.discovery.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openfeign 远程接口定义
 *
 * @author moyu.jun
 * @date 2022/3/16
 */
@FeignClient(value = "nacos-discovery-provider", fallback = ProviderFallback.class)
public interface RemoteProviderClient {

    /**
     * nacos-discovery-provider 服务提供者对应的 REST 接口
     *
     * @param message the message
     * @return result
     */
    @GetMapping("/nacos/discovery/provider/echo/{message}")
    String echo(@PathVariable String message);
}
