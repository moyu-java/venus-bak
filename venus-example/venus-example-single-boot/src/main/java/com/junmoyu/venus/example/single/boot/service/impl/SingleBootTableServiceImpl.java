package com.junmoyu.venus.example.single.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junmoyu.venus.example.single.boot.mapper.SingleBootTableMapper;
import com.junmoyu.venus.example.single.boot.model.dto.SingleBootPageQuery;
import com.junmoyu.venus.example.single.boot.model.entity.SingleBootTable;
import com.junmoyu.venus.example.single.boot.service.SingleBootTableService;
import com.junmoyu.venus.starter.core.exception.VenusException;
import com.junmoyu.venus.starter.core.model.dto.PageResult;
import com.junmoyu.venus.starter.core.model.dto.ResponseMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public int addObject(@NonNull SingleBootTable singleBootTable) {
        if(StringUtils.isBlank(singleBootTable.getName()) || StringUtils.isBlank(singleBootTable.getCode())){
            throw new VenusException(ResponseMessage.REQUIRED_PARAM_IS_NULL);
        }
        singleBootTable.setId(null);
        singleBootTable.setDeleted(false);
        singleBootTable.setCreateTime(new Date());
        singleBootTable.setUpdateTime(new Date());
        return singleBootTableMapper.insert(singleBootTable);
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
        QueryWrapper<SingleBootTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SingleBootTable.COL_IS_DELETED, false);
        queryWrapper.last("limit 10");
        return singleBootTableMapper.selectList(queryWrapper);
    }

    @Override
    public PageResult<SingleBootTable> page(@NonNull SingleBootPageQuery pageQuery) {
        QueryWrapper<SingleBootTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SingleBootTable.COL_IS_DELETED, false);
        if (StringUtils.isNotBlank(pageQuery.getName())) {
            queryWrapper.like(SingleBootTable.COL_NAME, "%" + pageQuery.getName() + "%");
        }
        if (pageQuery.getStartQueryTime() != null) {
            queryWrapper.gt(SingleBootTable.COL_CREATE_TIME, pageQuery.getStartQueryTime());
        }
        if (pageQuery.getEndQueryTime() != null) {
            queryWrapper.lt(SingleBootTable.COL_CREATE_TIME, pageQuery.getEndQueryTime());
        }
        Page<SingleBootTable> page = new Page<>(pageQuery.getCurrentPage(), pageQuery.getPageSize());
        Page<SingleBootTable> singleBootTablePage = singleBootTableMapper.selectPage(page, queryWrapper);
        return new PageResult<>(pageQuery.getCurrentPage(), pageQuery.getPageSize(), (int) singleBootTablePage.getTotal(), singleBootTablePage.getRecords());
    }
}
