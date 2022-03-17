package com.junmoyu.venus.example.single.boot.controller;

import com.junmoyu.venus.example.single.boot.model.dto.SingleBootPageQuery;
import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import com.junmoyu.venus.starter.core.model.dto.PageResult;
import com.junmoyu.venus.starter.core.model.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Restful http interface example.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@RestController
@RequestMapping("/single/boot/data")
@RequiredArgsConstructor
public class SingleBootController {

    private final SingleBootTableService singleBootTableService;

    @GetMapping("")
    public Response<List<SingleBootTable>> list() {
        return Response.success(singleBootTableService.list());
    }

    @GetMapping("/page")
    public Response<PageResult<SingleBootTable>> page(SingleBootPageQuery pageQuery) {
        return Response.success(singleBootTableService.page(pageQuery));
    }

    @GetMapping("/{id}")
    public Response<SingleBootTable> getObject(@PathVariable Long id) {
        return Response.success(singleBootTableService.getObject(id));
    }

    @PostMapping("")
    public Response<Boolean> addObject(@RequestBody SingleBootTable singleBootTable) {
        singleBootTableService.addObject(singleBootTable);
        return Response.success(true);
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable Long id) {
        singleBootTableService.delete(id);
        return Response.success(true);
    }

    @PutMapping("")
    public Response<Boolean> update(@RequestBody SingleBootTable singleBootTable) {
        singleBootTableService.update(singleBootTable);
        return Response.success(true);
    }
}
