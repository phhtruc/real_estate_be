package com.example.real_estate_be.repository;

import com.example.real_estate_be.entity.PropertyEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class PropertySpecification {

    public static Specification<PropertyEntity> hasTitle(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<PropertyEntity> hasPropertyType(Long propertyTypeId) {
        return (root, query, cb) ->
                propertyTypeId == null ? null : cb.equal(root.get("propertyType").get("id"), propertyTypeId);
    }

    public static Specification<PropertyEntity> hasPrice(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<PropertyEntity> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) return null;
            if (minPrice != null && maxPrice != null) return cb.between(root.get("price"), minPrice, maxPrice);
            return minPrice != null ? cb.greaterThanOrEqualTo(root.get("price"), minPrice) : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<PropertyEntity> hasAreaBetween(Double minArea, Double maxArea) {
        return (root, query, cb) -> {
            if (minArea == null && maxArea == null) return null;
            if (minArea != null && maxArea != null) return cb.between(root.get("area"), minArea, maxArea);
            return minArea != null ? cb.greaterThanOrEqualTo(root.get("area"), minArea) : cb.lessThanOrEqualTo(root.get("area"), maxArea);
        };
    }

    public static Specification<PropertyEntity> hasLocation(String address) {
        return (root, query, cb) ->
                address == null ? null : cb.like(cb.lower(root.get("address")), "%" + address.toLowerCase() + "%");
    }

    public static Specification<PropertyEntity> sortPrice(String order) {
        return (root, query, cb) -> {
            if (order == null) return null;
            if (order.equalsIgnoreCase("ASC")) {
                assert query != null;
                query.orderBy(cb.asc(root.get("price")));
            } else if (order.equalsIgnoreCase("DESC")) {
                assert query != null;
                query.orderBy(cb.desc(root.get("price")));
            }
            return null;
        };
    }

}
