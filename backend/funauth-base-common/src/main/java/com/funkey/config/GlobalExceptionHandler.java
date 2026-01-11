package com.funkey.config;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
        String detail = ex.getMostSpecificCause() != null
            ? ex.getMostSpecificCause().getMessage()
            : ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(400, "Data integrity violation", detail));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(500, "Internal server error", ex.getMessage()));
    }

    public static class ErrorResponse {
        private final int code;
        private final String message;
        private final String detail;
        private final LocalDateTime timestamp = LocalDateTime.now();

        public ErrorResponse(int code, String message, String detail) {
            this.code = code;
            this.message = message;
            this.detail = detail;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getDetail() {
            return detail;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
