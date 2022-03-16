package com.junmoyu.venus.example.nacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 测试参数更新后是否会再次执行此类的方法
 * 测试结果为: 不会重复执行
 *
 * @author moyu.jun
 * @date 2022/3/16
 */
@Slf4j
@Component
public class NacosConfigRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        log.warn("NacosConfigRunner running...");
    }
}
