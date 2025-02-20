package com.example.real_estate_be.controller;

import com.example.real_estate_be.dto.PropertyDTO.PropertyRequestDTO;
import com.example.real_estate_be.dto.PropertyDTO.PropertySearchDTO;
import com.example.real_estate_be.service.PropertyService;
import com.example.real_estate_be.utils.JsonResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/properties")
public class PropertyController {
    PropertyService propertyService;

    @GetMapping
    public ResponseEntity<?> getAllProperties(@RequestParam(required = false, defaultValue = "1") int page,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return JsonResponse.ok(propertyService.getAllProperties(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable Long id) {
        return JsonResponse.ok(propertyService.getPropertyById(id));
    }

    @PostMapping
    public ResponseEntity<?> addProperty(@RequestParam(value = "files", required = false) List<MultipartFile> files,
                                         @ModelAttribute @Valid PropertyRequestDTO requestDTO) {
        return JsonResponse.ok(propertyService.addProperty(requestDTO, files));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable Long id,
                                            @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                            @ModelAttribute @Valid PropertyRequestDTO requestDTO) {
        return JsonResponse.ok(propertyService.updateProperty(id, requestDTO, files));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return JsonResponse.ok("Deleted Property");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProperties(@Valid PropertySearchDTO searchDTO,
                                              @RequestParam(required = false, defaultValue = "1") int page,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize,
                                              @RequestParam(required = false) String sortPrice) {
        return JsonResponse.ok(propertyService.searchProperty(searchDTO, page, pageSize, sortPrice));
    }

}
