package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    private List<Showing> schedule;
    private double specialDiscount;
    private double happyHourDiscount;
    private double seventhDayFlat;
    private double seq0Flat;
    private double seq1Flat;

    public Theater(List<Showing> schedule) {
        this.schedule = schedule;
        specialDiscount = .8;
        seq0Flat = 3;
        seq1Flat = 2;
        happyHourDiscount = .7;
        seventhDayFlat = 1;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public double getTicketPrice(Showing showing) {
        int sequence = showing.getSequenceOfTheDay();
        double basePrice = showing.getMovie().getTicketPrice();
        double finalPrice = basePrice;
        finalPrice = Math.min(finalPrice, applyHappyHourDiscount(showing, basePrice));
        finalPrice = Math.min(finalPrice, applySpecialDiscount(showing, basePrice));
        finalPrice = Math.min(finalPrice, apply7thDayDiscount(showing, basePrice));
        finalPrice = Math.min(finalPrice, applySeq0Discount(sequence, basePrice));
        finalPrice = Math.min(finalPrice, applySeq1Discount(sequence, basePrice));
        return finalPrice;
    }

    private double applyHappyHourDiscount(Showing showing, double basePrice) {
        if (showing.isHappyHourShowing()) {
            return basePrice * happyHourDiscount;
        }
        return basePrice;
    }

    private double applySpecialDiscount(Showing showing, double basePrice) {
        if (showing.getMovie().getIsSpecial()) {
            return basePrice * specialDiscount;
        }
        return basePrice;
    }

    private double apply7thDayDiscount(Showing showing, double basePrice) {
        if (showing.isSeventhDayShowing()) {
            return basePrice - seventhDayFlat;
        }
        return basePrice;
    }

    private double applySeq0Discount(int sequence, double basePrice) {
        if (sequence == 0) {
            return basePrice - seq0Flat;
        }
        return basePrice;
    }

    private double applySeq1Discount(int sequence, double basePrice) {
        if (sequence == 1) {
            return basePrice - seq1Flat;
        }
        return basePrice;
    }

    public void printSchedule() {
        System.out.println(LocalDate.now());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovie().getTicketPrice())
        );
        System.out.println("===================================================");
    }

    public void printJsonSchedule() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        try {
            System.out.print(om.writerWithDefaultPrettyPrinter().writeValueAsString(schedule));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
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
        theater.printJsonSchedule();
    }
}
