package com.example.conferenceapp.reservation;

import com.example.conferenceapp.ConferenceAppApplication;
import com.example.conferenceapp.lecture.Lecture;
import com.example.conferenceapp.lecture.exception.LectureWIthFullSlotsException;
import com.example.conferenceapp.notification.NotificationUtils;
import com.example.conferenceapp.reservation.exception.InvalidUserLoginOrPasswordException;
import com.example.conferenceapp.reservation.exception.PlanCollisionException;
import com.example.conferenceapp.reservation.exception.ReservationNotFound;
import com.example.conferenceapp.reservation.exception.UserEmailCollisionException;
import com.example.conferenceapp.reservation.exception.UserLoginCollisionException;
import com.example.conferenceapp.user.User;
import com.example.conferenceapp.user.dto.UserEmailChangeRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        NotificationUtils.notifyUser(user.getEmail(), "Pomyślnie zapisano na kurs");
        return this.reservationRepository.save(reservation);
    }

    public Set<Lecture> getAllUserLectures(final String login) {
        return this.reservationRepository.findAllByUserLogin(login).stream()
                .map(reservation -> this.getLectureById(reservation.getLectureId()))
                .collect(Collectors.toSet());
    }

    public void cancelReservation(final long id, final User user) {
        var reservation = this.reservationRepository.findById(id).orElseThrow(
                () -> new ReservationNotFound("Rezerwacja o podanym identyfikatorze nie istnieje")
        );
        if(reservation.getUserEmail().equals(user.getEmail()) && reservation.getUserLogin().equals(user.getLogin())){
            var lectureId = reservation.getLectureId();
            ConferenceAppApplication.conferences.get(CONFERENCE_ID)
                    .getLectureById(lectureId)
                    .removeUser(user);
            this.reservationRepository.delete(reservation);
        }else{
            throw new InvalidUserLoginOrPasswordException("Podany login lub email jest niepoprawny");
        }
    }

    public void cancelAllReservations(final User user) {
        var reservations = this.reservationRepository.findAllByUserLogin(user.getLogin());
        ConferenceAppApplication.conferences.get(CONFERENCE_ID).getLectures()
                .forEach(lecture -> {
                    if(lecture.getParticipants().contains(user)){
                        lecture.removeUser(user);
                    }
                });
        this.reservationRepository.deleteAll(reservations);
    }

    @Transactional
    public void updateEmail(final UserEmailChangeRequest request){
        var userLogin = request.getUser().getLogin();
        var reservations = this.reservationRepository.findAllByUserLogin(userLogin);
        if(reservations.isEmpty()){
            throw new InvalidUserLoginOrPasswordException("Podany login lub email jest niepoprawny");
        }
        if(reservationRepository.existsByUserEmail(request.getNewEmail())){
            throw new UserEmailCollisionException("Podany email jest już zajęty");
        }
        ConferenceAppApplication.conferences.get(CONFERENCE_ID).getLectures()
                        .forEach(lecture -> lecture.getParticipants().forEach(user -> {
                            if(user.equals(request.getUser())){
                                user.setEmail(request.getNewEmail());
                            }
                        }));
        reservations.forEach(reservation -> reservation.setUserEmail(request.getNewEmail()));
    }

    public Set<User> getAllRegisteredUsers() {
        return StreamSupport.stream(this.reservationRepository.findAll().spliterator(), false)
                .map(reservation -> new User(reservation.getUserLogin(), reservation.getUserEmail()))
                .collect(Collectors.toSet());
    }

    private void addUser(final long lectureId, final User user) {
        var lecture = this.getLectureById(lectureId);
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
