package com.junmoyu.venus.example.dubbo.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dubbo provider example.
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@SpringBootApplication
@MapperScan("com.junmoyu.venus.example.dubbo.provider.mapper")
public class DubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
