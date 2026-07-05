package com.huy.spring.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppExeption extends RuntimeException{
    private ErrorCode errorCode;

    public AppExeption(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

