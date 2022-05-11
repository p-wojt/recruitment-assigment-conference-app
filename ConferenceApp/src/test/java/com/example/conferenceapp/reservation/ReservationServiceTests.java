package com.example.conferenceapp.reservation;

import com.example.conferenceapp.reservation.exception.UserLoginCollisionException;
import com.example.conferenceapp.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class ReservationServiceTests {

    private ReservationRepository reservationRepository;
    private ReservationService reservationService;

    @BeforeEach
    public void setup() {
        this.reservationRepository = Mockito.mock(ReservationRepository.class);
        this.reservationService = new ReservationService(reservationRepository);
    }

    @Test
    void addReservation_allParamsOk_reservationCorrectlyAdded() {
        //given
        final User user = new User("patryk", "patryk.wojtiuk@outlook.com");
        final Reservation expectedReservation = new Reservation(user.getLogin(), user.getEmail(), 1L);
        Mockito.when(reservationRepository.save(any())).thenReturn(expectedReservation);
        //when
        final Reservation actual = this.reservationService.reserve(1L, user);
        //then
        assertEquals(expectedReservation, actual);
    }

    @Test
    void addReservation_loginExistsInDatabase_throwsUserLoginCollisionException() {
        //given
        final User user = new User("patryk", "patryk.wojtiuk@outlook.com");
        Mockito.when(reservationRepository.findAll()).thenReturn(
                () -> List.of(new Reservation(user.getLogin(), "patryk@outlook.com", 2L)).iterator()
        );
        //when + then
        final Throwable exception = catchThrowable(() -> this.reservationService.reserve(1L, user));
        assertThat(exception)
                .isInstanceOf(UserLoginCollisionException.class)
                .hasMessage("Podany login jest już zajęty");
    }


    //testy...
}
