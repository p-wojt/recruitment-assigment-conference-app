package com.example.conferenceapp.reservation;

import com.example.conferenceapp.ConferenceAppApplication;
import com.example.conferenceapp.lecture.Lecture;
import com.example.conferenceapp.lecture.exception.LectureWIthFullSlotsException;
import com.example.conferenceapp.notification.NotificationUtils;
import com.example.conferenceapp.reservation.exception.PlanCollisionException;
import com.example.conferenceapp.reservation.exception.UserLoginCollisionException;
import com.example.conferenceapp.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.example.conferenceapp.Constants.CONFERENCE_ID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation reserve(final long lectureId, final User user) {
        if (this.checkIfUserLoginExistsAndEmailIsDifferent(user)) {
            throw new UserLoginCollisionException("Podany login jest już zajęty");
        }
        this.addUser(lectureId, user);
        final Reservation reservation = new Reservation(user.getLogin(), user.getEmail(), lectureId);
        NotificationUtils.notifyUser(user.getEmail(), "Pomyślnie zapisano na kurs!");
        return this.reservationRepository.save(reservation);
    }

    public Set<Lecture> getAllUserLectures(final String login) {
        return this.reservationRepository.findAllByUserLogin(login).stream()
                .map(reservation -> this.getLectureById(reservation.getPrelectionId()))
                .collect(Collectors.toSet());
    }

    private void addUser(final long lectureId, final User user) {
        Lecture lecture = this.getLectureById(lectureId);
        if (lecture.areFreeSlots()) {
            if (checkIfLectureCollide(user.getLogin(), lecture.getStartTime())) {
                throw new PlanCollisionException("Wystąpiła kolizja prelekcji w danej ścieżce");
            }
            lecture.addUser(user);
        } else {
            throw new LectureWIthFullSlotsException("Nie ma wolnych miejsc");
        }
    }

    private Lecture getLectureById(final long id) {
        return ConferenceAppApplication.conferences.get(CONFERENCE_ID).getLectureById(id);
    }

    private boolean checkIfLectureCollide(final String login, final LocalTime startTime) {
        return this.getAllUserLectures(login).stream()
                .anyMatch(lecture -> lecture.getStartTime().equals(startTime));
    }

    private boolean checkIfUserLoginExistsAndEmailIsDifferent(final User user) {
        return StreamSupport.stream(this.reservationRepository.findAll().spliterator(), false)
                .anyMatch(reservation -> reservation.getUserLogin().equals(user.getLogin()) && !reservation.getUserEmail().equals(user.getEmail()));
    }
}
