package com.huy.spring.exception;

import com.huy.spring.domain.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse<String> handlingRunTimeException(RuntimeException exception) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(400);
        response.setMessage("RuntimeException Error");
        response.setResponse(exception.getMessage());
        return response;
    }

    @ExceptionHandler(value = AppExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse<String> handlingAppException(AppExeption exception) {
        ApiResponse<String> response = new ApiResponse<>();
        ErrorCode errorCode = exception.getErrorCode();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return response;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse<Map<String, String>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ApiResponse<Map<String, String>> response = new ApiResponse<>();
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String enumKey = error.getDefaultMessage();
            ErrorCode errorCode = ErrorCode.INVALID_KEY;
            try {
                errorCode = ErrorCode.valueOf(enumKey);
            } catch (IllegalArgumentException e) {

            }
            response.setCode(errorCode.getCode());
            errors.put(fieldName, errorCode.getMessage());
        });
        response.setMessage("Validation failure");
        response.setResponse(errors);
        return response;
    }
}
