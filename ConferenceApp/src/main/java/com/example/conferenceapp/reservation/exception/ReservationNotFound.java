package com.example.conferenceapp.reservation.exception;

import com.example.conferenceapp.advice.resolver.APIErrorStatusNotFoundResolver;

public class ReservationNotFound extends RuntimeException implements APIErrorStatusNotFoundResolver {

    public ReservationNotFound(final String message) {
        super(message);
    }
}
