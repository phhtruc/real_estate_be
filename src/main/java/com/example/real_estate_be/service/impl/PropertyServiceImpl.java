package com.example.real_estate_be.service.impl;

import com.cloudinary.Cloudinary;
import com.example.real_estate_be.dto.PageResponse;
import com.example.real_estate_be.dto.PropertyDTO.PropertyRequestDTO;
import com.example.real_estate_be.dto.PropertyDTO.PropertyResponseDTO;
import com.example.real_estate_be.dto.PropertyDTO.PropertySearchDTO;
import com.example.real_estate_be.entity.PropertyEntity;
import com.example.real_estate_be.entity.PropertyImageEntity;
import com.example.real_estate_be.enums.ErrorCode;
import com.example.real_estate_be.exception.ApiException;
import com.example.real_estate_be.mapper.PropertyMapper;
import com.example.real_estate_be.repository.PropertyImageRepository;
import com.example.real_estate_be.repository.PropertyRepository;
import com.example.real_estate_be.repository.PropertySpecification;
import com.example.real_estate_be.repository.PropertyTypeRepository;
import com.example.real_estate_be.service.CloudinaryService;
import com.example.real_estate_be.service.ImageService;
import com.example.real_estate_be.service.PropertyService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyServiceImpl implements PropertyService {
    PropertyRepository propertyRepository;
    PropertyMapper propertyMapper;
    PropertyTypeRepository propertyTypeRepository;
    ImageService imageService;
    PropertyImageRepository propertyImageRepository;
    CloudinaryService cloudinary;

    @Override
    public PropertyResponseDTO addProperty(PropertyRequestDTO requestDTO, List<MultipartFile> multipartFile) throws ApiException {
        var propertyType = propertyTypeRepository.findByName(requestDTO.getPropertyType())
                .orElseThrow(() -> new ApiException(ErrorCode.PROPERTY_TYPE_EXISTED));
        var property = propertyMapper.toPropertyEntity(requestDTO);
        property.setIsDeleted(false);
        property.setPropertyType(propertyType);
        var propertyEntity = propertyRepository.save(property);
        // save image
        List<PropertyImageEntity> images = multipartFile.stream().map(image -> {
            try {
                String imageUrl = cloudinary.uploadImage(image);
                return PropertyImageEntity.builder()
                        .imageUrl(imageUrl)
                        .isDeleted(false)
                        .propertyEntity(propertyEntity)
                        .build();
            } catch (IOException e) {
                throw new ApiException(ErrorCode.UPLOAD_FAILED);
            }
        }).toList();
        propertyImageRepository.saveAll(images);

        var propertyResponseDTO = propertyMapper.toPropertyResponseDTO(propertyEntity);
        propertyResponseDTO.setImages(images.stream()
                .map(PropertyImageEntity::getImageUrl).collect(Collectors.toList()));

        return propertyResponseDTO;
    }

    @Override
    @Transactional
    public PropertyResponseDTO updateProperty(Long id, PropertyRequestDTO requestDTO, List<MultipartFile> multipartFile) {
        propertyRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.PROPERTY_EXISTED));
        var propertyType = propertyTypeRepository.findByName(requestDTO.getPropertyType())
                .orElseThrow(() -> new ApiException(ErrorCode.PROPERTY_TYPE_EXISTED));
        var propertyEntity = propertyMapper.toPropertyEntity(requestDTO);
        propertyEntity.setIsDeleted(false);
        propertyEntity.setId(id);
        propertyEntity.setPropertyType(propertyType);
        var propertyResponseDTO = propertyMapper.toPropertyResponseDTO(propertyRepository.save(propertyEntity));

        //update image
        if (multipartFile != null && !multipartFile.isEmpty()) {
            propertyImageRepository.deleteByPropertyEntity(propertyEntity);

            // Lưu ảnh mới
            List<PropertyImageEntity> images = multipartFile.stream().map(image -> {
                try {
                    String imageUrl = cloudinary.uploadImage(image);
                    return PropertyImageEntity.builder()
                            .imageUrl(imageUrl)
                            .isDeleted(false)
                            .propertyEntity(propertyEntity)
                            .build();
                } catch (IOException e) {
                    throw new ApiException(ErrorCode.UPLOAD_FAILED);
                }
            }).toList();

            propertyImageRepository.saveAll(images);
            propertyResponseDTO.setImages(images.stream()
                    .map(PropertyImageEntity::getImageUrl).collect(Collectors.toList()));
        }

        return propertyResponseDTO;
    }

    @Override
    public void deleteProperty(Long id) {
        var property = propertyRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.PROPERTY_EXISTED));
        List<PropertyImageEntity> images = propertyImageRepository.findAllByPropertyEntity(property);
        property.setIsDeleted(true);

        images.forEach(image -> {image.setIsDeleted(false);});

        propertyRepository.save(property);
        propertyImageRepository.saveAll(images);
    }

    @Override
    public PageResponse<?> getAllProperties(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);
        Page<PropertyEntity> propertyPage = propertyRepository.findAll(pageable);

        List<PropertyResponseDTO> propertyList = mapToPropertyResponseDTOList(propertyPage);

        return buildPageResponse(propertyList, page, pageSize, propertyPage.getTotalPages());
    }

    @Override
    public PageResponse<?> searchProperty(PropertySearchDTO searchDTO, int page, int pageSize, String sortPrice) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize);

        Specification<PropertyEntity> spec = Specification
                .where(PropertySpecification.hasTitle(searchDTO.getTitle()))
                .and(PropertySpecification.hasPriceBetween(searchDTO.getMinPrice(), searchDTO.getMaxPrice()))
                .and(PropertySpecification.hasAreaBetween(searchDTO.getMinArea(), searchDTO.getMaxArea()))
                .and(PropertySpecification.sortPrice(sortPrice))
                .and(PropertySpecification.hasLocation(searchDTO.getAddress()));

        Page<PropertyEntity> propertyPage = propertyRepository.findAll(spec, pageable);

        List<PropertyResponseDTO> propertyList = mapToPropertyResponseDTOList(propertyPage);

        return buildPageResponse(propertyList, page, pageSize, propertyPage.getTotalPages());
    }

    @Override
    public PropertyResponseDTO getPropertyById(Long id) {
        var property = propertyRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.PROPERTY_EXISTED));
        var propertyResponseDTO = propertyMapper.toPropertyResponseDTO(property);
        propertyResponseDTO.setImages(getImagesForProperty(property));
        return propertyResponseDTO;
    }

    private List<PropertyResponseDTO> mapToPropertyResponseDTOList(Page<PropertyEntity> propertyPage) {
        return propertyPage.stream()
                .map(property -> {
                    PropertyResponseDTO dto = propertyMapper.toPropertyResponseDTO(property);
                    dto.setImages(getImagesForProperty(property));
                    return dto;
                })
                .toList();
    }

    private List<String> getImagesForProperty(PropertyEntity property) {
        return propertyImageRepository.findAllByPropertyEntity(property)
                .stream()
                .map(PropertyImageEntity::getImageUrl)
                .toList();
    }

    private PageResponse<?> buildPageResponse(List<PropertyResponseDTO> items, int page, int pageSize, int totalPages) {
        return PageResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPage(totalPages)
                .items(items)
                .build();
    }
}
