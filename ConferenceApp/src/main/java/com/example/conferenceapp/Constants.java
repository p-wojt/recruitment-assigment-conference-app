package com.example.conferenceapp;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public final class Constants {
    public static final Long CONFERENCE_ID = 1L;
    public static final LocalDate DATE = LocalDate.of(2022, 6, 1);
    public static final LocalTime START_TIME = LocalTime.of(10, 0);
    public static final LocalTime END_TIME = LocalTime.of(15, 45);
    public static final LocalTime T10_00 = LocalTime.of(10, 0);
    public static final LocalTime T11_45 = LocalTime.of(11, 45);
    public static final LocalTime T12_00 = LocalTime.of(12, 0);
    public static final LocalTime T13_45 = LocalTime.of(13, 45);
    public static final LocalTime T14_00 = LocalTime.of(14, 0);
    public static final LocalTime T15_45 = LocalTime.of(15, 45);

    public static final String FILE_NAME = "powiadomienia.txt";

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.##");

    private Constants(){

    }
}
