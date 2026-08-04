package com.rz;


import java.util.Random;

public record SeatTwo (char rowMarker, int seatNumber, boolean isReserved) {

    public SeatTwo(char rowMarker, int seatNumber) {
      this(rowMarker, seatNumber,  new Random().nextBoolean());
    }
  }
