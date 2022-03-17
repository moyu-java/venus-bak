package com.junmoyu.venus.starter.core.model.dto;

import com.junmoyu.venus.starter.core.util.SqlUtils;

import java.util.Objects;

/**
 * 分页查询对象扩展
 * 添加排序字段、分组字段、开始时间、结束时间等
 *
 * @author moyu.jun
 * @date 2021/12/18
 */
public class ExtendPageQuery extends TimePageQuery {

    private static final long serialVersionUID = 366042550094360937L;

    public static final String ASC = "ASC";

    public static final String DESC = "DESC";

    private String orderBy;

    private String orderDirection;

    private String groupBy;

    private boolean needTotalCount;

    public ExtendPageQuery() {
        this.needTotalCount = true;
        this.orderDirection = ASC;
    }

    public ExtendPageQuery(final String orderBy, final String orderDirection, final String groupBy) {
        setOrderBy(orderBy);
        setOrderDirection(orderDirection);
        setGroupBy(groupBy);
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setOrderBy(String orderBy) {
        if (!Objects.isNull(orderBy) && SqlUtils.isValidSqlColumn(orderBy)) {
            this.orderBy = orderBy;
        }
    }

    public void setOrderDirection(String orderDirection) {
        if (ASC.equalsIgnoreCase(orderDirection) || DESC.equalsIgnoreCase(orderDirection)) {
            this.orderDirection = orderDirection;
        } else {
            this.orderDirection = ASC;
        }
    }

    public void setGroupBy(String groupBy) {
        if (!Objects.isNull(groupBy) && SqlUtils.isValidSqlColumn(groupBy)) {
            this.groupBy = groupBy;
        }
    }

    public void setNeedTotalCount(final Boolean needTotalCount) {
        this.needTotalCount = Objects.isNull(needTotalCount) || needTotalCount;
    }

    public boolean isNeedTotalCount() {
        return needTotalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtendPageQuery)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ExtendPageQuery that = (ExtendPageQuery) o;
        return needTotalCount == that.needTotalCount
                && Objects.equals(orderBy, that.orderBy)
                && Objects.equals(orderDirection, that.orderDirection)
                && Objects.equals(groupBy, that.groupBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderBy, orderDirection, groupBy, needTotalCount);
    }
}
