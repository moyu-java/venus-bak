package com.junmoyu.venus.example.single.boot.model.dto;

import com.junmoyu.venus.starter.core.model.dto.TimePageQuery;

/**
 * 分页查询请求实体
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
public class SingleBootPageQuery extends TimePageQuery {

    private static final long serialVersionUID = 4190947950489681322L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
