package com.huy.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_KEY(999, "INVALID KEY INPUT", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User already existed!", HttpStatus.BAD_REQUEST),
    ID_NOT_FOUND(404, "Id not found !", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(404, "User not found !", HttpStatus.NOT_FOUND),
    INVALID_FIRST_NAME(400, "FirstName can not empty", HttpStatus.BAD_REQUEST),
    INVALID_LAST_NAME(400, "LastName can not empty", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(400, "Username must at least 5 characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(400, "Password must at least 8 characters", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(401, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED(403, "Access Denied", HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND(404, "Role not found", HttpStatus.NOT_FOUND),
    PERMISSION_NOT_FOUND(404, "Permission not found", HttpStatus.NOT_FOUND),
    DELETE_FAILED(500, "Cant delete this item", HttpStatus.INTERNAL_SERVER_ERROR),
    PERMISSION_EXISTED(1003, "Permission existed", HttpStatus.BAD_REQUEST),
    NULL_POINTER_ERROR(9999, "Null pointer error", HttpStatus.BAD_REQUEST),
    DOB_NOT_VALID(400, "Invalid date of birth", HttpStatus.BAD_REQUEST),
    NULL_USERNAME(400, "Username can not be null", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
