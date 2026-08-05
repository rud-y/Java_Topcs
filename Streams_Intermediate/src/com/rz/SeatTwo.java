package com.rz;


import java.util.Random;

public record SeatTwo (char rowMarker, int seatNumber, boolean isReserved) {

    public SeatTwo(char rowMarker, int seatNumber) {
      this(rowMarker, seatNumber,  new Random().nextBoolean());
    }

  // For noneMatch() purposes - where noneBooked is true
//  public SeatTwo(char rowMarker, int seatNumber) {
//    this(rowMarker, seatNumber,  false);
//  }

}
