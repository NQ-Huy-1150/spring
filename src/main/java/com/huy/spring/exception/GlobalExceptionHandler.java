package com.huy.spring.exception;

import com.huy.spring.domain.dto.response.ApiResponse;
import com.huy.spring.domain.dto.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<?> handlingRunTimeException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.builder()
                        .code(500)
                        .message("Uncategorized error")
                        .build());
    }

    @ExceptionHandler(value = AppExeption.class)
    ResponseEntity<?> handlingAppException(AppExeption exception) {
        ApiResponse<String> response = new ApiResponse<>();
        ErrorCode errorCode = exception.getErrorCode();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(response);
    }
    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<?> handlingAuthorizationDeniedException(AuthorizationDeniedException exception) {
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED;
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(exception.getMessage())
                        .build());
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<?> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED;
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(exception.getMessage())
                        .build());
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    ResponseEntity<?> handlingIllegalArgumentException(IllegalArgumentException exception) {
        ErrorCode errorCode = ErrorCode.NULL_POINTER_ERROR;
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(exception.getMessage())
                        .build());
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
