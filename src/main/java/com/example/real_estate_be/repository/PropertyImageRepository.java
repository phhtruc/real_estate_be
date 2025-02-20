package com.example.real_estate_be.repository;

import com.example.real_estate_be.entity.PropertyEntity;
import com.example.real_estate_be.entity.PropertyImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImageEntity, Long> {
    void deleteByPropertyEntity(PropertyEntity propertyEntity);
    List<PropertyImageEntity> findAllByPropertyEntity(PropertyEntity propertyEntity);
}
