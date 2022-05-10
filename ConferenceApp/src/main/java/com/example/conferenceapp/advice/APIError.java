package com.example.conferenceapp.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class APIError {

    private HttpStatus status;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime occured;

    public APIError(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
        this.occured = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getOccured() {
        return occured;
    }
}
