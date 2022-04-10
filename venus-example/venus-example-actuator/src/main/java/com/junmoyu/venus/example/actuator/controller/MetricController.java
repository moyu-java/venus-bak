package com.junmoyu.venus.example.actuator.controller;

import com.junmoyu.venus.starter.core.model.dto.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moyu.jun
 * @date 2022/4/10
 */
@RestController
@RequestMapping("metrics")
public class MetricController {

    @GetMapping("/test")
    public Response<String> test() {
        return Response.success();
    }
}
