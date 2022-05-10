package com.example.conferenceapp.lecture;

import com.example.conferenceapp.user.User;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.conferenceapp.Constants.DECIMAL_FORMAT;

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

    public void removeUser(final User user){
        this.participants.remove(user);
    }

    public boolean areFreeSlots() {
        return participants.size() < 5;
    }

    public String percentageOfSlotsOccupied() {
        return DECIMAL_FORMAT.format(this.getParticipants().size() / 5.0 * 100) + "%";
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
