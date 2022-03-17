package com.junmoyu.venus.example.single.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * mapper interface.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
public interface SingleBootTableMapper extends BaseMapper<SingleBootTable> {

    /**
     * 批量插入
     *
     * @param list 数据列表
     * @return 插入结果
     */
    int batchInsert(@Param("list") List<SingleBootTable> list);
}