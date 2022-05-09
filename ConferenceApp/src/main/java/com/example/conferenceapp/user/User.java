package com.example.conferenceapp.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @Email(message = "Provided email is not an email")
    private String email;

    public User() {
    }

    public void setEmail(final String email){
        this.email = email;
    }
}
