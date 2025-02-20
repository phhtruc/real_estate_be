package com.example.real_estate_be.service;

import com.example.real_estate_be.dto.PageResponse;
import org.springframework.stereotype.Service;

@Service
public interface PropertyTypeService {

    PageResponse<?> getAllPropertyType(int page, int pageSize);
}
