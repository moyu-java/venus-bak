package com.junmoyu.venus.gateway;

import com.junmoyu.venus.starter.core.exception.ExceptionHandlers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring cloud gateway application.
 *
 * @author moyu.jun
 * @date 2022/3/18
 */
@SpringBootApplication(exclude = {ExceptionHandlers.class })
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
