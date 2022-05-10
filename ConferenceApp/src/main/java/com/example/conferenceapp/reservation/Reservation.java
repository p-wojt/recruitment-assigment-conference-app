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

    private Long lectureId;

    public Reservation() {
    }

    public Reservation(final String userLogin, final String userEmail, final Long lectureId) {
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.lectureId = lectureId;
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

    public Long getLectureId() {
        return lectureId;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }
}
