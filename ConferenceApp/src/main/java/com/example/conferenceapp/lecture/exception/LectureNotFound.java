package com.example.conferenceapp.lecture.exception;

public class LectureNotFound extends RuntimeException {

    public LectureNotFound(final String message) {
        super(message);
    }
}
