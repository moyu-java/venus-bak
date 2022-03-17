package com.junmoyu.venus.starter.core.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 带时间的查询条件
 *
 * @author moyu.jun
 * @date 2021/12/18
 */
public class TimeQuery implements Serializable {

    private static final long serialVersionUID = 3745423051302901255L;

    private Date startQueryTime;

    private Date endQueryTime;

    public TimeQuery() {
    }

    public TimeQuery(final Date startQueryTime, final Date endQueryTime) {
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
        if (!(o instanceof TimeQuery)) {
            return false;
        }
        TimeQuery timeQuery = (TimeQuery) o;
        return Objects.equals(startQueryTime, timeQuery.startQueryTime)
                && Objects.equals(endQueryTime, timeQuery.endQueryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startQueryTime, endQueryTime);
    }
}
