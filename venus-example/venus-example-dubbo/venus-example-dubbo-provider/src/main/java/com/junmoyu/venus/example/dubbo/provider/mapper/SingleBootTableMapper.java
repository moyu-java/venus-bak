package com.junmoyu.venus.example.dubbo.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.junmoyu.venus.example.dubbo.api.model.entity.SingleBootTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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