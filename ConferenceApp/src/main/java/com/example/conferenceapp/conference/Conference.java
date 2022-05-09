package com.example.conferenceapp.conference;

import com.example.conferenceapp.lecture.Lecture;
import com.example.conferenceapp.lecture.exception.LectureNotFound;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Conference {
    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private List<Lecture> lectures;

    public Conference(final LocalDate date, final LocalTime startTime, final LocalTime endTime, List<Lecture> lectures) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lectures = lectures;
    }

    public Lecture getLectureById(final long id){
        return lectures.stream().filter(lecture -> lecture.getId() == id).findFirst().orElseThrow(
                () -> new LectureNotFound("Prelekcja o podanym identyfikatorze nie istnieje")
        );
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }
}
