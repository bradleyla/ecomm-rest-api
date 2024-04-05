package com.cogent.ecommrestapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ECommerceApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ECommerceApiException(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
    }

    public ECommerceApiException(String customMessage, HttpStatus status, String message) {
        super(customMessage);
        this.message = message;
        this.status = status;
    }
}
