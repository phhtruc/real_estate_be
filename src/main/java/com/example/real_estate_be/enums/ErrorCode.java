package com.example.real_estate_be.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_DATA(1001, "Invalid data", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1002, "You do not have permission", HttpStatus.FORBIDDEN),

    // property 11**
    PROPERTY_EXISTED(1101, "Property existed", HttpStatus.BAD_REQUEST),

    // property image 12**
    PROPERTY_IMAGE_EXISTED(1201, "Property image existed", HttpStatus.BAD_REQUEST),

    UPLOAD_FAILED(1301, "upload failed", HttpStatus.BAD_REQUEST),

    PROPERTY_TYPE_EXISTED(1401, "Property type existed", HttpStatus.BAD_REQUEST),
    ;
    Integer code;
    String message;
    HttpStatusCode statusCode;
}
