package com.example.real_estate_be.mapper;

import com.example.real_estate_be.dto.PropertyTypeDTO.PropertyTypeResponseDTO;
import com.example.real_estate_be.entity.PropertyTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropertyTypeMapper {
    PropertyTypeResponseDTO toPropertyTypeResponseDTO(PropertyTypeEntity propertyTypeEntity);
}
