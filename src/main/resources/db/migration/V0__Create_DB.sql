CREATE
DATABASE IF NOT EXISTS real_estate;

CREATE TABLE property_types
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime NULL,
    modified_date datetime NULL,
    modified_by   VARCHAR(255) NULL,
    name          VARCHAR(255) NOT NULL,
    is_deleted    BIT(1) NULL,
    CONSTRAINT pk_property_types PRIMARY KEY (id)
);

ALTER TABLE property_types
    ADD CONSTRAINT uc_property_types_name UNIQUE (name);

CREATE TABLE users
(
    id            BINARY(16)   NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime NULL,
    modified_date datetime NULL,
    modified_by   VARCHAR(255) NULL,
    full_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    phone_number  VARCHAR(255) NULL,
    is_deleted    BIT(1) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

CREATE TABLE properties
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_by        VARCHAR(255)          NULL,
    created_date      datetime              NULL,
    modified_date     datetime              NULL,
    modified_by       VARCHAR(255)          NULL,
    title             VARCHAR(255)          NULL,
    `description`     TEXT                  NULL,
    district          VARCHAR(255)          NULL,
    province          VARCHAR(255)          NULL,
    address           VARCHAR(255)          NULL,
    property_type_id  BIGINT                NULL,
    area              FLOAT                 NULL,
    price             DECIMAL               NULL,
    house_direction   VARCHAR(255)          NULL,
    balcony_direction VARCHAR(255)          NULL,
    legal_status      VARCHAR(255)          NULL,
    furniture         VARCHAR(255)          NULL,
    num_bedrooms      INT                   NULL,
    num_bathrooms     INT                   NULL,
    floor_count       INT                   NULL,
    is_deleted        BIT(1)                NULL,
    CONSTRAINT pk_properties PRIMARY KEY (id)
);

ALTER TABLE properties
    ADD CONSTRAINT FK_PROPERTIES_ON_PROPERTY_TYPE FOREIGN KEY (property_type_id) REFERENCES property_types (id);