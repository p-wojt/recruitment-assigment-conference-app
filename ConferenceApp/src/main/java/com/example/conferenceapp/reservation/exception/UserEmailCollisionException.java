package com.example.conferenceapp.reservation.exception;

public class UserEmailCollisionException extends RuntimeException {

    public UserEmailCollisionException(final String message) {
        super(message);
    }
}
