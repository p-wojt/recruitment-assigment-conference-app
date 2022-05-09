package com.example.conferenceapp.reservation.exception;

public class PlanCollisionException extends RuntimeException {
    public PlanCollisionException(final String message) {
        super(message);
    }
}
