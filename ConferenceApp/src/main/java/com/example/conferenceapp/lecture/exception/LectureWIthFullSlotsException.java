package com.example.conferenceapp.lecture.exception;

import com.example.conferenceapp.advice.resolver.APIErrorStatusNotFoundResolver;

public class LectureWIthFullSlotsException extends RuntimeException implements APIErrorStatusNotFoundResolver {
    public LectureWIthFullSlotsException(final String message) {
        super(message);
    }
}
