package com.example.conferenceapp.reservation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userLogin;

    private String userEmail;

    private Long prelectionId;

    public Reservation() {
    }

    public Reservation(final String userLogin, final String userEmail, final Long prelectionId) {
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.prelectionId = prelectionId;
    }

    public Long getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getPrelectionId() {
        return prelectionId;
    }
}
