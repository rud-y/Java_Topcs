package com.rz;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.stream.IntStream;

// Uses SeatTwo record !!

public class StreamTerminal {
  public static void main(String[] args) {
    var result = IntStream
            .iterate(0, i -> i <= 1000, i -> i = i + 3 )
            .summaryStatistics();
    System.out.println("Result = " + result);

    var leapYearData = IntStream
            .iterate(2000, i -> i <= 2025, i -> i +1)
            .filter(i -> i % 4 == 0)
//            .peek(System.out::println)
            .summaryStatistics();
    System.out.println("Leap Year data: " + leapYearData);


    SeatTwo[] seats = new SeatTwo[100];
    Arrays.setAll(seats, i -> new SeatTwo((char) ('A' + i / 10), i % 10 + 1));
//    Arrays.asList(seats).forEach(System.out::println);

    long reservationCount = Arrays
            .stream(seats)
            .filter(SeatTwo::isReserved)
            .count();
    System.out.println("reservationCount: " + reservationCount);

    //----- anyMatch and allMatch stream operations
    boolean hasBookings = Arrays
            .stream(seats)
            .anyMatch(SeatTwo::isReserved);
    System.out.println("hasBookings : " + hasBookings);

    boolean fullyBooked = Arrays
            .stream(seats)
            .allMatch(SeatTwo::isReserved);
    System.out.println("fullyBooked : " + fullyBooked);

  }
}


