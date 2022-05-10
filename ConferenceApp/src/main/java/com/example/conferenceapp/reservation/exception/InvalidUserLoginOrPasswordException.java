package com.example.conferenceapp.reservation.exception;

public class InvalidUserLoginOrPasswordException extends RuntimeException {

    public InvalidUserLoginOrPasswordException(final String message) {
        super(message);
    }
}
