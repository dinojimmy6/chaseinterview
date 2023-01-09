package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public boolean isHappyHourShowing() {
        return showStartTime.getHour() >= 11 && showStartTime.getHour() < 16;
    }

    public boolean isSeventhDayShowing() {
        return showStartTime.getDayOfMonth() == 7;
    }
}
