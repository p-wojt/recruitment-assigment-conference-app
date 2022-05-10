package com.example.conferenceapp.reservation;

import com.example.conferenceapp.lecture.Lecture;
import com.example.conferenceapp.user.User;
import com.example.conferenceapp.user.dto.UserEmailChangeRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/user/lectures")
    public ResponseEntity<Set<Lecture>> getAllUserLectures(@Param("login") final String login){
        return ResponseEntity.ok(this.reservationService.getAllUserLectures(login));
    }

    @PostMapping("/lecture/{id}")
    public ResponseEntity<Reservation> saveReservation(@PathVariable("id") final long lectureId, @Valid @RequestBody final User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/lecture" + lectureId).toUriString());
        return ResponseEntity.created(uri).body(this.reservationService.reserve(lectureId, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable final long id, @Valid @RequestBody final User user){
        this.reservationService.cancelReservation(id, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> cancelAllReservations(@Valid @RequestBody final User user){
        this.reservationService.cancelAllReservations(user);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/emailChange")
    public ResponseEntity<?> updateUserEmail(@RequestBody UserEmailChangeRequest request){
        this.reservationService.updateEmail(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<Set<User>> getAllRegisteredUsers(){
        return ResponseEntity.ok(this.reservationService.getAllRegisteredUsers());
    }

    @GetMapping(params = "lecturesStats")
    public ResponseEntity<Map<Long, String>> getLecturesStats(){
        return ResponseEntity.ok(this.reservationService.getLecturesStats());
    }
}
