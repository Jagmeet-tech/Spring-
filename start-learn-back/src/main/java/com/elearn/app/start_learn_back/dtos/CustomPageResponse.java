package com.elearn.app.start_learn_back.dtos;

import java.util.List;

public class CustomPageResponse<T> {

    private int pageSize;
    private int totalElements;

    public CustomPageResponse(){

    }

    public CustomPageResponse(int pageSize, boolean last, int totalPages, int totalElements, List<T> content,int pageNumber) {
        this.pageSize = pageSize;
        this.last = last;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.content = content;
        this.pageNumber = pageNumber;
    }

    private int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }


    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    private boolean last;
    private int pageNumber;

    public boolean getLast() {
        return this.last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private List<T> content;

}
