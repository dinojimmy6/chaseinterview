package com.jpmc.theater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShowingTests {
    @Test
    void nonHappyHourShowing() {
        Showing showing = new Showing(Movie.TURNING_RED, 0, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(9, 0)));
        Assertions.assertFalse(showing.isHappyHourShowing());
    }

    @Test
    void happyHourShowing() {
        Showing showing = new Showing(Movie.TURNING_RED, 0, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(14, 0)));
        Assertions.assertTrue(showing.isHappyHourShowing());
    }

    @Test
    void seventhDayShowing() {
        Showing showing = new Showing(Movie.TURNING_RED, 0, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(14, 0)));
        Assertions.assertTrue(showing.isSeventhDayShowing());
    }

    @Test
    void nonSeventhDayShowing() {
        Showing showing = new Showing(Movie.TURNING_RED, 0, LocalDateTime.of(LocalDate.of(2023, 1, 16), LocalTime.of(14, 0)));
        Assertions.assertFalse(showing.isSeventhDayShowing());
    }
}
