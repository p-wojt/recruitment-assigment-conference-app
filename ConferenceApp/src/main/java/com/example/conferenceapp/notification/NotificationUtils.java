package com.example.conferenceapp.notification;

import java.io.FileWriter;
import java.io.IOException;

import static com.example.conferenceapp.Constants.FILE_NAME;

public final class NotificationUtils {

    public static void notifyUser(final String email, final String message) {
        var notification = new Notification(email, message);

        try (final FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
            fileWriter.write(notification + "\n");
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    private NotificationUtils() {

    }
}
