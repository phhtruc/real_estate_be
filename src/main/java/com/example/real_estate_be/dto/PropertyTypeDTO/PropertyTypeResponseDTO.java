package com.example.real_estate_be.dto.PropertyTypeDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyTypeResponseDTO {
    Long id;
    String name;
}
