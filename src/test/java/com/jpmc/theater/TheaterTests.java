package com.jpmc.theater;

import com.jpmc.theater.testUtils.TestSchedules;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void reservationFeeNoDiscount() {
        Theater theater = new Theater(TestSchedules.schedule);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 5, 4);
        assertEquals(Movie.THE_BATMAN.getTicketPrice() * 4, reservation.totalFee(theater));
    }

    @Test
    void reservationFee7thDiscountOnly() {
        Theater theater = new Theater(TestSchedules.schedule7th);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 8, 4);
        assertEquals(32, reservation.totalFee(theater));
    }

    @Test
    void reservationFee7thHappyHourOverride() {
        Theater theater = new Theater(TestSchedules.schedule7th);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 3, 4);
        assertEquals(11 * .7 * 4, reservation.totalFee(theater));
    }

    @Test
    void reservationFeeSeq0Discount() {
        Theater theater = new Theater(TestSchedules.schedule7th);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 0, 3);
        assertEquals((Movie.TURNING_RED.getTicketPrice() - 3) * 3, reservation.totalFee(theater));
    }

    @Test
    void reservationFeeSeq1Discount() {
        Theater theater = new Theater(TestSchedules.schedule);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 1, 5);
        assertEquals((Movie.THE_BATMAN.getTicketPrice() - 2) * 5, reservation.totalFee(theater));
    }

    @Test
    void reservationFeeSpecialDiscount() {
        Theater theater = new Theater(TestSchedules.schedule7th);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 7, 5);
        assertEquals(Movie.SPIDERMAN.getTicketPrice() * .8 * 5, reservation.totalFee(theater));
    }

    @Test
    void printMovieSchedule() {
        List<Showing> schedule = List.of(
                new Showing(Movie.TURNING_RED, 0, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0))),
                new Showing(Movie.SPIDERMAN, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0))),
                new Showing(Movie.THE_BATMAN, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50))),
                new Showing(Movie.TURNING_RED, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30))),
                new Showing(Movie.SPIDERMAN, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10))),
                new Showing(Movie.THE_BATMAN, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50))),
                new Showing(Movie.TURNING_RED, 6, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30))),
                new Showing(Movie.SPIDERMAN, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 10))),
                new Showing(Movie.THE_BATMAN, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)))
        );
        Theater theater = new Theater(schedule);
        theater.printSchedule();
    }
}
