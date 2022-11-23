package com.my.tictactoe.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

@Data
public class ApiErrorResponse {

    private final int status;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ValidationError> errors;

    @Getter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) errors = new ArrayList<>();

        errors.add(new ValidationError(field, message));
    }

    public static ResponseEntity<Object> build(String message, HttpStatus httpStatus, List<FieldError> fieldErrors) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(httpStatus.value(), message);

        for (FieldError fieldError : fieldErrors) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    public static ResponseEntity<Object> build(String message, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(
                new ApiErrorResponse(httpStatus.value(), message)
        );
    }
}
