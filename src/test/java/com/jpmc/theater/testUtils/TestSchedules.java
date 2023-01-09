package com.jpmc.theater.testUtils;

import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TestSchedules {
    public final static List<Showing> schedule = List.of(
            new Showing(Movie.TURNING_RED, 0, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(9, 0))),
            new Showing(Movie.THE_BATMAN, 1, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(10, 0))),
            new Showing(Movie.THE_BATMAN, 2, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(12, 50))),
            new Showing(Movie.TURNING_RED, 3, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(14, 30))),
            new Showing(Movie.SPIDERMAN, 4, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(16, 10))),
            new Showing(Movie.THE_BATMAN, 5, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(17, 50))),
            new Showing(Movie.TURNING_RED, 6, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(19, 30))),
            new Showing(Movie.SPIDERMAN, 7, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(21, 10))),
            new Showing(Movie.THE_BATMAN, 8, LocalDateTime.of(LocalDate.of(2023, 1, 15), LocalTime.of(23, 0)))
    );

    public final static List<Showing> schedule7th = List.of(
            new Showing(Movie.TURNING_RED, 0, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(9, 0))),
            new Showing(Movie.SPIDERMAN, 1, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(11, 0))),
            new Showing(Movie.THE_BATMAN, 2, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(12, 50))),
            new Showing(Movie.TURNING_RED, 3, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(14, 30))),
            new Showing(Movie.SPIDERMAN, 4, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(16, 10))),
            new Showing(Movie.THE_BATMAN, 5, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(17, 50))),
            new Showing(Movie.TURNING_RED, 6, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(19, 30))),
            new Showing(Movie.SPIDERMAN, 7, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(21, 10))),
            new Showing(Movie.THE_BATMAN, 8, LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(23, 0)))
    );
}
