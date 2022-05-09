package com.example.conferenceapp;

import com.example.conferenceapp.conference.Conference;
import com.example.conferenceapp.lecture.Lecture;
import com.example.conferenceapp.lecture.LectureType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

import static com.example.conferenceapp.Constants.*;

@SpringBootApplication
public class ConferenceAppApplication {

    public static final Map<Long, Conference> conferences = Map.of(CONFERENCE_ID, new Conference(
            DATE,
            START_TIME,
            END_TIME,
            List.of(
                    new Lecture(1L, T10_00, T11_45, LectureType.FRONTEND),
                    new Lecture(2L, T10_00, T11_45, LectureType.BACKEND),
                    new Lecture(3L, T10_00, T11_45, LectureType.DEVOPS),

                    new Lecture(4L, T12_00, T13_45, LectureType.FRONTEND),
                    new Lecture(5L, T12_00, T13_45, LectureType.BACKEND),
                    new Lecture(6L, T12_00, T13_45, LectureType.DEVOPS),

                    new Lecture(7L, T14_00, T15_45, LectureType.FRONTEND),
                    new Lecture(8L, T14_00, T15_45, LectureType.BACKEND),
                    new Lecture(9L, T14_00, T15_45, LectureType.DEVOPS)
            )));

    public static void main(String[] args) {
        SpringApplication.run(ConferenceAppApplication.class, args);
    }

}
