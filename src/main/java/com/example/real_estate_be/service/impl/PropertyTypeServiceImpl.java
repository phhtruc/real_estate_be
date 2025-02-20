package com.example.real_estate_be.service.impl;

import com.example.real_estate_be.dto.PageResponse;
import com.example.real_estate_be.dto.PropertyTypeDTO.PropertyTypeResponseDTO;
import com.example.real_estate_be.entity.PropertyTypeEntity;
import com.example.real_estate_be.mapper.PropertyTypeMapper;
import com.example.real_estate_be.repository.PropertyTypeRepository;
import com.example.real_estate_be.service.PropertyTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PropertyTypeServiceImpl implements PropertyTypeService {
    PropertyTypeRepository propertyTypeRepository;
    PropertyTypeMapper propertyTypeMapper;

    @Override
    public PageResponse<?> getAllPropertyType(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);
        Page<PropertyTypeEntity> propertyPage = propertyTypeRepository.findAll(pageable);

        List<PropertyTypeResponseDTO> list = propertyPage
                .map(propertyTypeMapper::toPropertyTypeResponseDTO).stream().collect(Collectors.toList());

        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(propertyPage.getTotalPages())
                .items(list)
                .build();
    }
}
