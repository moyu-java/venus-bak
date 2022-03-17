package com.junmoyu.venus.example.single.boot;

import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * 单体 Spring Boot 单元测试
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor
public class SingleBootTest {

    private final SingleBootTableService singleBootTableService;

    @Test
    public void addSingleBootTable() {
        SingleBootTable singleBootTable = SingleBootTable.builder()
                .name("James")
                .code("dake_code")
                .banner("https://image.junmoyu.com/123456.png")
                .icon("fa-user")
                .secret("TQYx61Laba0c95HGj6Zw")
                .description("fake_data")
                .sort(10)
                .deleted(false)
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        singleBootTableService.addObject(singleBootTable);
    }
}
