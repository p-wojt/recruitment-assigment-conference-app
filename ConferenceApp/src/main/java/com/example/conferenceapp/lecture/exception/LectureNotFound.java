package com.example.conferenceapp.lecture.exception;

import com.example.conferenceapp.advice.resolver.APIErrorStatusNotFoundResolver;

public class LectureNotFound extends RuntimeException implements APIErrorStatusNotFoundResolver {

    public LectureNotFound(final String message) {
        super(message);
    }
}
