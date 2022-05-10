package com.example.conferenceapp.advice.resolver;

import com.example.conferenceapp.advice.APIErrorStatusResolver;
import org.springframework.http.HttpStatus;

public interface APIErrorStatusUnauthorizedResolver extends APIErrorStatusResolver {
    default HttpStatus resolveHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
