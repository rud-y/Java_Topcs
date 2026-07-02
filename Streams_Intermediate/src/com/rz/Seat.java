package com.rz;

//public record Seat(char rowMarker, int seatNumber, double price) {
//
//  public Seat(char rowMarker, int seatNumber) {
//    this(rowMarker, seatNumber, rowMarker > 'C' && (seatNumber <= 2 || seatNumber >= 9) ? 50 : 75);
//  }
//
//  @Override
//  public String toString() {
//    return "%c%03 %.0f".formatted(rowMarker, seatNumber, price);
//  }
//}

import java.util.Objects;

public final class Seat {
  private final char rowMarker;
  private final int seatNumber;
  private final double price;

  Seat(char rowMarker, int seatNumber, double price) {
    this.rowMarker = rowMarker;
    this.seatNumber = seatNumber;
    this.price = price;
  }

  public char rowMarker() {
    return rowMarker;
  }

  public int seatNumber() {
    return seatNumber;
  }

  public double price() {
    return price;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Seat) obj;
    return this.rowMarker == that.rowMarker &&
            this.seatNumber == that.seatNumber &&
            Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rowMarker, seatNumber, price);
  }


  public Seat(char rowMarker, int seatNumber) {
    this(rowMarker, seatNumber, rowMarker > 'C' && (seatNumber <= 2 || seatNumber >= 9) ? 50 : 75);
  }

  @Override
  public String toString() {
    return String.format("%c%03d %.0f", rowMarker, seatNumber, price);
  }
}

