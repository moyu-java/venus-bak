package com.junmoyu.venus.starter.core.model.dto;

import java.util.Date;
import java.util.Objects;

/**
 * 带查询时间的分页查询对象
 *
 * @author moyu.jun
 * @date 2022/3/11
 */
public class TimePageQuery extends PageQuery {

    private static final long serialVersionUID = -999529364840163259L;
    
    private Date startQueryTime;

    private Date endQueryTime;

    public TimePageQuery() {
    }

    public TimePageQuery(Integer currentPage, Integer pageSize) {
        super(currentPage, pageSize);
    }

    public TimePageQuery(Date startQueryTime, Date endQueryTime) {
        this.startQueryTime = startQueryTime;
        this.endQueryTime = endQueryTime;
    }

    public TimePageQuery(Integer currentPage, Integer pageSize, Date startQueryTime, Date endQueryTime) {
        super(currentPage, pageSize);
        this.startQueryTime = startQueryTime;
        this.endQueryTime = endQueryTime;
    }

    public Date getStartQueryTime() {
        return startQueryTime;
    }

    public void setStartQueryTime(Date startQueryTime) {
        this.startQueryTime = startQueryTime;
    }

    public Date getEndQueryTime() {
        return endQueryTime;
    }

    public void setEndQueryTime(Date endQueryTime) {
        this.endQueryTime = endQueryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimePageQuery)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TimePageQuery that = (TimePageQuery) o;
        return Objects.equals(startQueryTime, that.startQueryTime)
                && Objects.equals(endQueryTime, that.endQueryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startQueryTime, endQueryTime);
    }
}
