package com.example.conferenceapp.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepostiory userRepostiory;

    public UserService(final UserRepostiory userRepostiory) {
        this.userRepostiory = userRepostiory;
    }
}
