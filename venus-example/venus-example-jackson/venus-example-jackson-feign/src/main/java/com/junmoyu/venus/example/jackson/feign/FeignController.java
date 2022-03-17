package com.junmoyu.venus.example.jackson.feign;

import com.junmoyu.venus.starter.core.model.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * feign controller.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@Slf4j
@RestController
@RequestMapping("feign")
@RequiredArgsConstructor
public class FeignController {

    private final RemoteRestService remoteRestService;

    @GetMapping("/params")
    public Response<RestDTO> test1(RestDTO query) {
        log.info(query.toString());
        return remoteRestService.test1(query);
    }

    @GetMapping("/body")
    public Response<RestDTO> test2(@RequestBody RestDTO query) {
        log.info(query.toString());
        return remoteRestService.test2(query);
    }

    @GetMapping("/request/param")
    public Response<RestDTO> getDate(@RequestParam("date") LocalDate date, @RequestParam("dateTime") LocalDateTime dateTime,
                                     @RequestParam("originalDate") Date originalDate) {
        log.info(date.toString());
        log.info(dateTime.toString());
        log.info(originalDate.toString());
        return remoteRestService.getDate(date, dateTime, originalDate);
    }
    @GetMapping("/request/param/date")
    public Response<RestDTO> getDate(@RequestParam("originalDate") Date originalDate) {
        log.info(originalDate.toString());
        return remoteRestService.getDate(originalDate);
    }
}
