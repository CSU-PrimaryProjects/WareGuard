package com.school.manager.common;

import lombok.Data;

import java.util.List;

@Data
public class PageDataDto<T> {

    /**
     * 总记录数
     */
    private long total;
    /**
     * 数据集合
     */
    private List<T> list;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 一页数量
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pages;

    public PageDataDto(long total, List<T> list, int pageNum, int pageSize, int pages) {
        this.total = total;
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
    }
}