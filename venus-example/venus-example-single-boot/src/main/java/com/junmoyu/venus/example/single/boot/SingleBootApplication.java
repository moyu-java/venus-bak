package com.junmoyu.venus.example.single.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 单体 Spring Boot 示例应用.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@SpringBootApplication
@MapperScan("com.junmoyu.venus.example.single.boot.mapper")
public class SingleBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SingleBootApplication.class, args);
    }
}
