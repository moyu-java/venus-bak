package com.junmoyu.venus.example.nacos.discovery.consumer.feign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * nacos discovery consumer controller with openfeign.
 *
 * @author moyu.jun
 * @date 2022/3/16
 */
@Slf4j
@RestController
@RequestMapping("/nacos/discovery/consumer/feign")
@RequiredArgsConstructor
public class OpenFeignController {

    private final RemoteProviderClient remoteProviderClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/echo")
    public String echo() {
        return remoteProviderClient.echo(applicationName);
    }
}
