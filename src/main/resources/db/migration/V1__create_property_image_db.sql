CREATE TABLE property_images
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime NULL,
    modified_date datetime NULL,
    modified_by   VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    property_id   BIGINT NULL,
    is_deleted    BIT(1) NULL,
    CONSTRAINT pk_property_images PRIMARY KEY (id)
);

ALTER TABLE property_images
    ADD CONSTRAINT FK_PROPERTY_IMAGES_ON_PROPERTY FOREIGN KEY (property_id) REFERENCES properties (id);