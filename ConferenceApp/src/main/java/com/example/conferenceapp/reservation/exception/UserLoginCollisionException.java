package com.example.conferenceapp.reservation.exception;

import com.example.conferenceapp.advice.resolver.APIErrorStatusConflictResolver;

public class UserLoginCollisionException extends RuntimeException implements APIErrorStatusConflictResolver {
    public UserLoginCollisionException(final String message) {
        super(message);
    }
}
