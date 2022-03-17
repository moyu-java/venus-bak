package com.junmoyu.venus.starter.core.model.dto;

import java.io.Serializable;
import java.util.*;

/**
 * 分页返回结果
 *
 * @author moyu.jun
 * @date 2021/12/18
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -4136474757414698827L;

    private int currentPage;

    private int pageSize;

    private int totalCount;

    private Collection<T> list;

    public PageResult() {
    }

    public PageResult(final int currentPage, final int pageSize, final int totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public PageResult(final int currentPage, final int pageSize, final int totalCount, final Collection<T> list) {
        this(currentPage, pageSize, totalCount);
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return this.totalCount % this.pageSize == 0 ? this.totalCount
                / this.pageSize : (this.totalCount / this.pageSize) + 1;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<T> getList() {
        return null == list ? Collections.emptyList() : new ArrayList<>(list);
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage <= 0 ? 1 : currentPage;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return list == null || list.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PageResult)) {
            return false;
        }
        PageResult<?> that = (PageResult<?>) o;
        return currentPage == that.currentPage
                && pageSize == that.pageSize
                && totalCount == that.totalCount
                && Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPage, pageSize, totalCount, list);
    }
}
