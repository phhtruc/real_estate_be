package com.example.real_estate_be.service;

import com.example.real_estate_be.dto.PageResponse;
import com.example.real_estate_be.dto.PropertyDTO.PropertyRequestDTO;
import com.example.real_estate_be.dto.PropertyDTO.PropertyResponseDTO;
import com.example.real_estate_be.dto.PropertyDTO.PropertySearchDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PropertyService {
    PropertyResponseDTO addProperty(PropertyRequestDTO requestDTO, List<MultipartFile> multipartFile);
    PropertyResponseDTO updateProperty(Long id, PropertyRequestDTO requestDTO, List<MultipartFile> multipartFile);
    void deleteProperty(Long id);
    PageResponse<?> getAllProperties(int page, int pageSize);
    PageResponse<?> searchProperty(PropertySearchDTO searchDTO, int page, int pageSize, String sortPrice);

    PropertyResponseDTO getPropertyById(Long id);
}
