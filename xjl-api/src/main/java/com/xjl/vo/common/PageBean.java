package com.xjl.vo.common;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @描述: 分页组件.
 * @作者: xiejianlun .
 * @版本: 1.0 .
 */
@ApiModel(value = "分页数据")
public class PageBean<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8470697978259453214L;
    @ApiModelProperty(value = "总记录数")
    private long total; // 总记录数
    @ApiModelProperty(value = "分页中的数据")
    private List<T> items; // 结果集
    @ApiModelProperty(value = "第几页")
    private int pageNumber; // 第几页
    @ApiModelProperty(value = "每页记录数")
    private int pageSize; // 每页记录数
    @ApiModelProperty(value = "总页数")
    private int pages; // 总页数
    @ApiModelProperty(value = "当前页的数量")
    private int size; // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性

    public PageBean() {
    }

    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理， 而出现一些问题。
     *
     * @param list page结果
     */
    public PageBean(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNumber = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.items = page;
            this.size = page.size();
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
