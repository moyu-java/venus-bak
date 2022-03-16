package com.junmoyu.venus.example.nacos.discovery.provider;

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
@RestController
@RequestMapping("/nacos/discovery/provider")
public class NacosDiscoveryProviderController {

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message) {
        return "Hello nacos discovery. message is " + message;
    }
}
