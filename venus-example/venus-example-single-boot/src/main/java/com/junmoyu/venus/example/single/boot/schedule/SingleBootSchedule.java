package com.junmoyu.venus.example.single.boot.schedule;

import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * schedule task example.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SingleBootSchedule {

    private final SingleBootTableService singleBootTableService;

    @Scheduled(initialDelay = 5 * 1000, fixedDelay = 60 * 1000 * 1000)
    public void executeFixedDelay() {
        List<SingleBootTable> list = singleBootTableService.list();
        if (CollectionUtils.isEmpty(list)) {
            log.error("ScheduleFixedDelay: single boot table data list is empty.");
        } else {
            log.info("ScheduleFixedDelay: single boot table list size is {}.", list.size());
        }
    }

    @Scheduled(cron = "0 0 1 * * ? ")
    public void executeCron() {
        List<SingleBootTable> list = singleBootTableService.list();
        if (CollectionUtils.isEmpty(list)) {
            log.error("ScheduleCron: single boot table data list is empty.");
        } else {
            log.info("ScheduleCron: single boot table list size is {}.", list.size());
        }
    }
}
