package com.junmoyu.venus.example.single.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junmoyu.venus.example.single.boot.mapper.SingleBootTableMapper;
import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import com.junmoyu.venus.starter.core.exception.VenusException;
import com.junmoyu.venus.starter.core.model.dto.ResponseMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * service interface implement.
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SingleBootTableServiceImpl implements SingleBootTableService {

    private final SingleBootTableMapper singleBootTableMapper;

    @Override
    public SingleBootTable getObject(@NonNull Long id) {
        return singleBootTableMapper.selectById(id);
    }

    @Override
    public void addObject(@NonNull SingleBootTable singleBootTable) {
        singleBootTable.setId(null);
        singleBootTable.setDeleted(false);
        singleBootTable.setCreateTime(new Date());
        singleBootTable.setUpdateTime(new Date());
        singleBootTableMapper.insert(singleBootTable);
    }

    @Override
    public void delete(@NonNull Long id) {
        singleBootTableMapper.deleteById(id);
    }

    @Override
    public void update(@NonNull SingleBootTable singleBootTable) {
        if (Objects.isNull(singleBootTable.getId())) {
            throw new VenusException(ResponseMessage.FAILED);
        }
        singleBootTable.setDeleted(null);
        singleBootTable.setCreateTime(null);
        singleBootTable.setUpdateTime(new Date());
        singleBootTableMapper.updateById(singleBootTable);
    }

    @Override
    public List<SingleBootTable> list() {
        log.info("info");
        log.warn("warn");
        log.debug("debug");
        log.error("error");
        QueryWrapper<SingleBootTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SingleBootTable.COL_IS_DELETED, false);
        queryWrapper.last("limit 10");
        return singleBootTableMapper.selectList(queryWrapper);
    }
}
