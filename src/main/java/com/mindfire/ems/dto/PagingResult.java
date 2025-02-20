package com.mindfire.ems.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingResult<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int size;
    private int page;

    public PagingResult(List<T> content, int totalPages, long totalElements, int size, int page) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.page = page + 1;
    }
}
