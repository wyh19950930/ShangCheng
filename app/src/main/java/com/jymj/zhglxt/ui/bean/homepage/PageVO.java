package com.jymj.zhglxt.ui.bean.homepage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-17
 * "分页"
 */
public class PageVO<T> implements Serializable {

    //("内容")
    private List<T> content;

    //("总页数")
    private Integer totalPages;

    //("总条数")
    private Long totalElements;

    //("第几页")
    private Integer number;

    //("每页多少条")
    private Integer size;

    //("是否是第一页")
    private Boolean first;

    //("是否是最后一页")
    private Boolean last;

    //("在总数里有多少条")
    private Integer numberOfElements;

    //("是否是空")
    private Boolean empty;

    public Integer getNumber() {
        return this.number + 1;
    }

    public List<T> getContent() {
        if (content==null){
            content = new ArrayList<>();
        }
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public PageVO(List<T> content, Integer totalPages, Long totalElements, Integer number, Integer size, Boolean first, Boolean last, Integer numberOfElements, Boolean empty) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.number = number;
        this.size = size;
        this.first = first;
        this.last = last;
        this.numberOfElements = numberOfElements;
        this.empty = empty;
    }
}
