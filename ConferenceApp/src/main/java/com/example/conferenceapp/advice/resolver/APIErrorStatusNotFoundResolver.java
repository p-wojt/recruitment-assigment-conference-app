package com.example.conferenceapp.advice.resolver;

import com.example.conferenceapp.advice.APIErrorStatusResolver;
import org.springframework.http.HttpStatus;

public interface APIErrorStatusNotFoundResolver extends APIErrorStatusResolver {
    default HttpStatus resolveHttpStatus(){
        return HttpStatus.NOT_FOUND;
    }
}
