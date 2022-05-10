package com.example.conferenceapp.advice.resolver;

import com.example.conferenceapp.advice.APIErrorStatusResolver;
import org.springframework.http.HttpStatus;

public interface APIErrorStatusConflictResolver extends APIErrorStatusResolver {
    default HttpStatus resolveHttpStatus(){
        return HttpStatus.CONFLICT;
    }
}
