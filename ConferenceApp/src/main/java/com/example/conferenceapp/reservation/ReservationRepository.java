package com.example.conferenceapp.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Set<Reservation> findAllByUserLogin(final String login);

    boolean existsByUserEmail(final String email);
}
