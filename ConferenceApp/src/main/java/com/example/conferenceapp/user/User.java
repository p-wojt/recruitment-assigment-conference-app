package com.example.conferenceapp.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

    @NotEmpty(message = "Login nie może być pusty")
    @Size(min = 3, max = 24, message = "Login musi od 3 do 24 znaków")
    private String login;

    @Email(message = "Podany email nie jest właściwy")
    private String email;

    public User(final String login, final String email) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}
