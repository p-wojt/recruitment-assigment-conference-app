package com.example.conferenceapp.advice;

import com.example.conferenceapp.lecture.exception.LectureNotFound;
import com.example.conferenceapp.lecture.exception.LectureWIthFullSlotsException;
import com.example.conferenceapp.reservation.exception.InvalidUserLoginOrPasswordException;
import com.example.conferenceapp.reservation.exception.PlanCollisionException;
import com.example.conferenceapp.reservation.exception.ReservationNotFound;
import com.example.conferenceapp.reservation.exception.UserEmailCollisionException;
import com.example.conferenceapp.reservation.exception.UserLoginCollisionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceExceptionController {

    @ExceptionHandler(value = {
            LectureNotFound.class,
            LectureWIthFullSlotsException.class,
            InvalidUserLoginOrPasswordException.class,
            PlanCollisionException.class,
            ReservationNotFound.class,
            UserEmailCollisionException.class,
            UserLoginCollisionException.class,
    })
    public ResponseEntity<APIError> handleError(Exception exception) {
        final HttpHeaders headers = new HttpHeaders();
        final HttpStatus status = defineStatus(exception);
        final APIError body = new APIError(status, exception.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    private HttpStatus defineStatus(Exception exception){
        if(exception instanceof APIErrorStatusResolver){
            return ((APIErrorStatusResolver) exception).resolveHttpStatus();
        }
        return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    }

}
