package com.example.real_estate_be.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class PageResponse<T> implements Serializable {
    private int page;
    private int pageSize;
    private int totalPage;
    private T items;

}
