package com.example.real_estate_be.controller;

import com.example.real_estate_be.service.PropertyTypeService;
import com.example.real_estate_be.utils.JsonResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/property-type")
public class PropertyTypeController {

    PropertyTypeService propertyTypeService;

    @GetMapping
    public ResponseEntity<?> getAllPropertyType(@RequestParam(required = false, defaultValue = "1") int page,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return JsonResponse.ok(propertyTypeService.getAllPropertyType(page, pageSize));
    }
}
