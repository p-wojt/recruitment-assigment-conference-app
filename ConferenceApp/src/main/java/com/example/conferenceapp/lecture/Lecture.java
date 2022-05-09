package com.example.conferenceapp.lecture;

import com.example.conferenceapp.user.User;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Lecture {

    private Long id;

    private LocalTime startTime;

    private LocalTime endTime;

    private LectureType lectureType;

    private List<User> participants;

    public Lecture(final Long id, final LocalTime startTime, final LocalTime endTime, final LectureType lectureType) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lectureType = lectureType;
        this.participants = new ArrayList<>();
    }

    public void addUser(final User user) {
        this.participants.add(user);
    }

    public boolean areFreeSlots() {
        return participants.size() < 5;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public List<User> getParticipants() {
        return participants;
    }
}
