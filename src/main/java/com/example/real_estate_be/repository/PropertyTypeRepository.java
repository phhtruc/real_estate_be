package com.example.real_estate_be.repository;

import com.example.real_estate_be.entity.PropertyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyTypeEntity, Long> {
    Optional<PropertyTypeEntity> findByName(String name);
}
