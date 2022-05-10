package com.example.conferenceapp.user.dto;

import com.example.conferenceapp.user.User;

import javax.validation.Valid;

public class UserEmailChangeRequest {

    @Valid
    private User user;

    private String newEmail;

    public User getUser() {
        return user;
    }

    public String getNewEmail() {
        return newEmail;
    }
}
