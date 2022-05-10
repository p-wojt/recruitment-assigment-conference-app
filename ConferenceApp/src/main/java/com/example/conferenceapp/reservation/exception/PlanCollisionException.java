package com.example.conferenceapp.reservation.exception;

import com.example.conferenceapp.advice.resolver.APIErrorStatusConflictResolver;

public class PlanCollisionException extends RuntimeException implements APIErrorStatusConflictResolver {
    public PlanCollisionException(final String message) {
        super(message);
    }
}
