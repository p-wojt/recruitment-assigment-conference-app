package com.example.conferenceapp.notification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notification {

    private final LocalDateTime localDateTime;

    private final String email;

    private final String message;

    public Notification(final String email, final String message) {
        this.localDateTime = LocalDateTime.now();
        this.email = email;
        this.message = message;
    }

    @Override
    public String toString() {
        return "[" + this.localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] Powiadomienie dla " + this.email + " Wiadomość: " + this.message;
    }
}
