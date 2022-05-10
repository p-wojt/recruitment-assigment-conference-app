package com.example.conferenceapp.reservation.exception;

import com.example.conferenceapp.advice.resolver.APIErrorStatusConflictResolver;

public class UserEmailCollisionException extends RuntimeException implements APIErrorStatusConflictResolver {

    public UserEmailCollisionException(final String message) {
        super(message);
    }
}
