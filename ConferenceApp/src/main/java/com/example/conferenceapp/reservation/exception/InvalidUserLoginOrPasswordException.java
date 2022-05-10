package com.example.conferenceapp.reservation.exception;

import com.example.conferenceapp.advice.resolver.APIErrorStatusUnauthorizedResolver;

public class InvalidUserLoginOrPasswordException extends RuntimeException implements APIErrorStatusUnauthorizedResolver {

    public InvalidUserLoginOrPasswordException(final String message) {
        super(message);
    }
}
