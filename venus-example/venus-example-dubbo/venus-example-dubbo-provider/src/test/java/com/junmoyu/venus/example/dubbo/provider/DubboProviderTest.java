package com.junmoyu.venus.example.dubbo.provider;

import com.junmoyu.venus.example.dubbo.api.SingleBootTableService;
import com.junmoyu.venus.example.dubbo.api.model.entity.SingleBootTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Dubbo service test.
 *
 * @author moyu.jun
 * @date 2022/3/24
 */
@Slf4j
@SpringBootTest
public class DubboProviderTest {

    @Resource
    private SingleBootTableService singleBootTableService;

    @Test
    public void getTest() {
        SingleBootTable object = singleBootTableService.getObject(3L);
        Assertions.assertNotNull(object);
    }

}
