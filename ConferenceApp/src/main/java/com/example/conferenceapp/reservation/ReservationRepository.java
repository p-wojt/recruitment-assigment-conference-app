package com.example.conferenceapp.reservation;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Set<Reservation> findAllByUserLogin(final String login);

    boolean existsByUserEmail(final String email);
}
