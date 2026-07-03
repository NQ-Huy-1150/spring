package com.huy.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_KEY(999, "INVALID KEY INPUT"),
    USER_EXISTED(1002, "User already existed!"),
    ID_NOT_FOUND(404, "Id not found !"),
    USER_NOT_FOUND(404, "User not found !"),
    INVALID_FIRST_NAME(400, "FirstName can not empty"),
    INVALID_LAST_NAME(400, "LastName can not empty"),
    INVALID_USERNAME(400, "Username must at least 5 characters"),
    INVALID_PASSWORD(400, "Password must at least 8 characters"),
    ;
    private int code;
    private String message;
}
