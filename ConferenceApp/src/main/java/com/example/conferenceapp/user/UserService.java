package com.example.conferenceapp.user;

import com.example.conferenceapp.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepostiory userRepostiory;

    public UserService(final UserRepostiory userRepostiory) {
        this.userRepostiory = userRepostiory;
    }

    @Transactional
    public void changeEmail(final long id, final String email) {
        User user = this.userRepostiory.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
        user.setEmail(email);
    }
}
