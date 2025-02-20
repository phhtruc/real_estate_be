package com.example.real_estate_be.dto.PropertyDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyRequestDTO {
    String title;
    String description;
    String propertyType;
    String district;
    String province;
    String address;
    String area;
    String price;
    String houseDirection;
    String balconyDirection;
    String legalStatus;
    String furniture;
    String numBedrooms;
    String numBathrooms;
    String floorCount;
}
