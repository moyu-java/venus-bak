package com.junmoyu.venus.example.jackson.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * jackson feign example.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@EnableFeignClients
@SpringBootApplication
public class JacksonFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(JacksonFeignApplication.class, args);
    }
}
