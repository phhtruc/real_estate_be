package com.example.real_estate_be.mapper;

import com.example.real_estate_be.dto.PropertyDTO.PropertyRequestDTO;
import com.example.real_estate_be.dto.PropertyDTO.PropertyResponseDTO;
import com.example.real_estate_be.entity.PropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  PropertyMapper {

    PropertyResponseDTO toPropertyResponseDTO(PropertyEntity property);

    @Mapping(target = "propertyType", ignore = true) // Bỏ qua propertyType
    PropertyEntity toPropertyEntity(PropertyRequestDTO propertyRequestDTO);

}


