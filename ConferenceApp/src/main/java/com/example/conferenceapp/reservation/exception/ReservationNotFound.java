package com.example.conferenceapp.reservation.exception;

public class ReservationNotFound extends RuntimeException {

    public ReservationNotFound(final String message) {
        super(message);
    }
}
