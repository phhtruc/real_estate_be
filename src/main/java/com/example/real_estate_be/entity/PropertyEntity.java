package com.example.real_estate_be.entity;

import com.example.real_estate_be.enums.BalconyDirection;
import com.example.real_estate_be.enums.FurnitureStatus;
import com.example.real_estate_be.enums.HouseDirection;
import com.example.real_estate_be.enums.LegalStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
public class PropertyEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private PropertyTypeEntity propertyType;

//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private UserEntity owner;

    @Column(name = "area")
    private Float area;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "house_direction")
    private HouseDirection houseDirection;

    @Enumerated(EnumType.STRING)
    @Column(name = "balcony_direction")
    private BalconyDirection balconyDirection;

    @Enumerated(EnumType.STRING)
    @Column(name = "legal_status")
    private LegalStatus legalStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "furniture")
    private FurnitureStatus furniture;

    @Column(name = "num_bedrooms")
    private Integer numBedrooms;

    @Column(name = "num_bathrooms")
    private Integer numBathrooms;

    @Column(name = "floor_count")
    private Integer floorCount;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
