package com.junmoyu.venus.starter.core.model.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * 基础分页查询对象
 *
 * @author moyu.jun
 * @date 2022/3/11
 */
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 437062142215284866L;

    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final int MAX_PAGE_SIZE = 100;

    private int currentPage;

    private int pageSize;

    public PageQuery() {
        this.currentPage = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public PageQuery(final Integer currentPage, final Integer pageSize) {
        setCurrentPage(currentPage);
        setPageSize(pageSize);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public void setCurrentPage(final Integer currentPage) {
        this.currentPage = Objects.isNull(currentPage) || currentPage <= 0 ? 1 : currentPage;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = Objects.isNull(pageSize) || pageSize <= 0 || pageSize > MAX_PAGE_SIZE ? DEFAULT_PAGE_SIZE : pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PageQuery)) {
            return false;
        }
        PageQuery that = (PageQuery) o;
        return currentPage == that.currentPage && pageSize == that.pageSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPage, pageSize);
    }
}
