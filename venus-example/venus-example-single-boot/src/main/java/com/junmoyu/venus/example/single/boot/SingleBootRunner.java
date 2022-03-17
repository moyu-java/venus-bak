package com.junmoyu.venus.example.single.boot;

import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * application runner example.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SingleBootRunner implements ApplicationRunner {

    private final SingleBootTableService singleBootTableService;

    @Override
    public void run(ApplicationArguments args) {
        List<SingleBootTable> list = singleBootTableService.list();
        if (CollectionUtils.isEmpty(list)) {
            log.error("SingleBootRunner: single boot table data list is empty.");
        } else {
            log.info("SingleBootRunner: single boot table list size is {}.", list.size());
        }
    }
}
