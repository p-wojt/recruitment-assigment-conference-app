package com.example.conferenceapp.reservation.exception;

public class UserLoginCollisionException extends RuntimeException {
    public UserLoginCollisionException(final String message) {
        super(message);
    }
}
