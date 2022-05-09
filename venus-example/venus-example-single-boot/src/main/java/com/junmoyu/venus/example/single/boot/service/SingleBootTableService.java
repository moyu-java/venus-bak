package com.junmoyu.venus.example.single.boot.service;

import com.junmoyu.venus.example.single.boot.model.dto.SingleBootPageQuery;
import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.starter.core.model.dto.PageResult;
import com.junmoyu.venus.starter.core.model.dto.TimePageQuery;
import lombok.NonNull;

import java.util.List;

/**
 * service interface.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
public interface SingleBootTableService {

    /**
     * 获取单个数据
     *
     * @param id 主键
     * @return {@link SingleBootTable}
     */
    SingleBootTable getObject(@NonNull Long id);

    /**
     * 添加数据
     *
     * @param singleBootTable 数据对象
     */
    int addObject(@NonNull SingleBootTable singleBootTable);

    /**
     * 删除数据
     *
     * @param id 主键
     */
    void delete(@NonNull Long id);

    /**
     * 更新数据
     *
     * @param singleBootTable 数据对象
     */
    void update(@NonNull SingleBootTable singleBootTable);

    /**
     * 获取数据列表
     *
     * @return 数据列表
     */
    List<SingleBootTable> list();

    /**
     * 分页查询
     *
     * @param pageQuery 分页查询请求实体
     * @return 分页查询结果
     */
    PageResult<SingleBootTable> page(@NonNull SingleBootPageQuery pageQuery);
}
