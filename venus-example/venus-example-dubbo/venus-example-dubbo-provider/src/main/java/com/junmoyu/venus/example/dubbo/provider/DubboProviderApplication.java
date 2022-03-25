package com.junmoyu.venus.example.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author moyu.jun
 * @date 2022/3/24
 */
@EnableDubbo
@SpringBootApplication
@MapperScan("com.junmoyu.venus.example.dubbo.provider.mapper")
public class DubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
