package com.example.real_estate_be.dto.PropertyDTO;

import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertySearchDTO {
    String title;
    String propertyType;
    @Min(value = 0, message = "Min price must be greater than or equal to 0")
    Double minPrice;
    @Min(value = 0, message = "Max price must be greater than or equal to 0")
    Double maxPrice;
    @Min(value = 0, message = "Min area must be greater than or equal to 0")
    Double minArea;
    @Min(value = 0, message = "Max area must be greater than or equal to 0")
    Double maxArea;
    String address;
    String district;
    String province;
    String houseDirection;
    String balconyDirection;
    String legalStatus;
    String furniture;
    Integer numBedrooms;
    Integer numBathrooms;
    Integer floorCount;
}