package com.junmoyu.venus.example.single.boot.controller;

import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import com.junmoyu.venus.starter.core.model.dto.Response;
import com.junmoyu.venus.starter.core.model.dto.ResponseMessage;
import com.junmoyu.venus.starter.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * redis example.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@Slf4j
@RestController
@RequestMapping("/single/boot/data/cache")
@RequiredArgsConstructor
public class RedisController {

    private final SingleBootTableService singleBootTableService;

    private final RedisService redisService;

    private static final String REDIS_KEY_PREFIX = "venus:example:single-boot";

    @GetMapping("/{id}")
    public Response<SingleBootTable> getObject(@PathVariable Long id) {
        if (Objects.isNull(id)) {
            return Response.failure(ResponseMessage.REQUIRED_PARAM_IS_NULL);
        }
        SingleBootTable cacheSingleBootTable = redisService.getCacheMapValue(REDIS_KEY_PREFIX, String.valueOf(id));
        if (Objects.isNull(cacheSingleBootTable)) {
            log.info("redis cache is null, get data from mysql.");
            SingleBootTable singleBootTable = singleBootTableService.getObject(id);
            redisService.setCacheMapValue(REDIS_KEY_PREFIX, String.valueOf(id), singleBootTable);
            return Response.success(singleBootTable);
        }
        log.info("redis cache is not null, return cache data.");
        return Response.success(cacheSingleBootTable);
    }
}
