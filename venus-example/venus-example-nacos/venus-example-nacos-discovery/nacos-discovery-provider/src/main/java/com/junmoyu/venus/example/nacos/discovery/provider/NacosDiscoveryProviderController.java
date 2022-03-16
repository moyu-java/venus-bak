package com.junmoyu.venus.example.nacos.discovery.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * nacos discovery provider controller.
 *
 * @author moyu.jun
 * @date 2022/3/16
 */
@Slf4j
@RestController
@RequestMapping("/nacos/discovery/provider")
public class NacosDiscoveryProviderController {

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message) {
        log.info("Hello nacos discovery. message is {}.", message);
        return "Hello nacos discovery. message is " + message;
    }
}
