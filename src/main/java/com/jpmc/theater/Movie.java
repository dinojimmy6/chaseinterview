package com.jpmc.theater;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Duration;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Movie {
    THE_BATMAN("The Batman", Duration.ofMinutes(95), 9, false),
    TURNING_RED("Turning Red", Duration.ofMinutes(85), 11, false),
    SPIDERMAN("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true);

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private boolean isSpecial;

    Movie(String title, Duration runningTime, double ticketPrice, boolean isSpecial) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.isSpecial = isSpecial;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public boolean getIsSpecial() {
        return isSpecial;
    }
}