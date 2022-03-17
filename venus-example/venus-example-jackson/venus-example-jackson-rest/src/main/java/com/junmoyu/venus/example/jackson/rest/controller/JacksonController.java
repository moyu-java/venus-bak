package com.junmoyu.venus.example.jackson.rest.controller;

import com.junmoyu.venus.example.jackson.rest.model.RestDTO;
import com.junmoyu.venus.starter.core.model.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * jackson 接口序列化测试
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@Slf4j
@RestController
@RequestMapping("jackson")
public class JacksonController {

    @GetMapping("/params")
    public Response<RestDTO> test1(RestDTO query) {
        log.info(query.toString());
        return Response.success(getRestDTO());
    }

    @GetMapping("/body")
    public Response<RestDTO> test2(@RequestBody RestDTO query) {
        log.info(query.toString());
        return Response.success(getRestDTO());
    }

    @GetMapping("/request/param")
    public Response<RestDTO> getDate(@RequestParam("date") LocalDate date,
                                     @RequestParam("dateTime") LocalDateTime dateTime,
                                     @RequestParam("originalDate") Date originalDate) {
        log.info(date.toString());
        log.info(dateTime.toString());
        log.info(originalDate.toString());
        return Response.success(getRestDTO());
    }

    @GetMapping("/request/param/date")
    public Response<RestDTO> getDate(@RequestParam("originalDate") Date originalDate) {
        log.info(originalDate.toString());
        return Response.success(getRestDTO());
    }

    private RestDTO getRestDTO() {
        return RestDTO.builder()
                .strParam("string param.")
                .intParam(99)
                .longParam(9223372036854775807L)
                .booleanParam(true)
                .bigIntegerParam(new BigInteger("1222233333333333333"))
                .bigDecimalParam(new BigDecimal("0.000001"))
                .localDate(LocalDate.now())
                .localTime(LocalTime.now())
                .localDateTime(LocalDateTime.now())
                .date(new Date()).build();
    }
}
