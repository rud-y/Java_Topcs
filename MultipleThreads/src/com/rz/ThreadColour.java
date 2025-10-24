package com.rz;

public enum ThreadColour {

  ANSI_RESET("\u001B[0m"),
  ANSI_BLACK("\u001B[30m"),
  ANSI_WHITE("\u001B[37m"),
  ANSI_BLUE("\u001B[34m"),
  ANSI_CYAN("\u001B[36m"),
  ANSI_GREEN("\u001B[32m"),
  ANSI_RED("\u001B[31m"),
  ANSI_PURPLE("\u001B[35m"),
  ANSI_YELLOW("\u001b[33m");


  private final String color;

  ThreadColour(String color) {
    this.color = color;
  }

  public String color() {
    return color;
  }


}
