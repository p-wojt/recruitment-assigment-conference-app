package com.example.conferenceapp.advice;

import org.springframework.http.HttpStatus;

public interface APIErrorStatusResolver {
    HttpStatus resolveHttpStatus();
}
