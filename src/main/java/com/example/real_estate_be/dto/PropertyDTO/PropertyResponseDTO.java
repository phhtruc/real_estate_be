package com.example.real_estate_be.dto.PropertyDTO;

import com.example.real_estate_be.entity.PropertyImageEntity;
import com.example.real_estate_be.entity.PropertyTypeEntity;
import com.example.real_estate_be.enums.BalconyDirection;
import com.example.real_estate_be.enums.FurnitureStatus;
import com.example.real_estate_be.enums.HouseDirection;
import com.example.real_estate_be.enums.LegalStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyResponseDTO {

     Long id;
     String title;
     String description;
     PropertyTypeEntity propertyType;
     String district;
     String province;
     String address;
     Float area;
     BigDecimal price;
     HouseDirection houseDirection;
     BalconyDirection balconyDirection;
     LegalStatus legalStatus;
     FurnitureStatus furniture;
     Integer numBedrooms;
     Integer numBathrooms;
     Integer floorCount;
     List<String> images;
}
