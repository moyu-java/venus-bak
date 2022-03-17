package com.junmoyu.venus.example.jackson.feign;

import com.junmoyu.venus.starter.core.model.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * feign 接口定义
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@FeignClient(name = "jackson-test", url = "http://localhost:8088")
public interface RemoteRestService {

    /**
     * GET 请求参数在 URL 拼接
     *
     * @param record 请求参数
     * @return 请求结果
     */
    @GetMapping("/jackson/params")
    Response<RestDTO> test1(@SpringQueryMap RestDTO record);

    /**
     * GET 请求不能包含 request body.
     *
     * @param record 请求参数
     * @return 异常
     */
    @GetMapping("/jackson/body")
    Response<RestDTO> test2(@RequestBody RestDTO record);

    /**
     * GET 请求使用 RequestParam 注解
     * RequestParam 必须添加 value.否则报错.
     *
     * @param date         date
     * @param dateTime     dateTime
     * @param originalDate originalDate
     * @return 结果
     */
    @GetMapping("/jackson/request/param")
    Response<RestDTO> getDate(@RequestParam("date") LocalDate date, @RequestParam("dateTime") LocalDateTime dateTime, @RequestParam("originalDate") Date originalDate);

    /**
     * GET 请求使用 RequestParam 注解
     * RequestParam 必须添加 value.否则报错.
     *
     * @param originalDate originalDate
     * @return 结果
     */
    @GetMapping("/jackson/request/param/date")
    Response<RestDTO> getDate(@RequestParam("originalDate") Date originalDate);
}
