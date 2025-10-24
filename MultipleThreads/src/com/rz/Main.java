package com.rz;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

      Stopwatch greenStopWatch = new Stopwatch(TimeUnit.SECONDS);
      Stopwatch purpleStopWatch = new Stopwatch(TimeUnit.SECONDS);
      Stopwatch redStopWatch = new Stopwatch(TimeUnit.SECONDS);

      Thread green = new Thread(greenStopWatch::countDown, ThreadColour.ANSI_GREEN.name());
      Thread purple = new Thread(() -> purpleStopWatch.countDown(7), ThreadColour.ANSI_PURPLE.name());
      Thread red = new Thread(redStopWatch::countDown, ThreadColour.ANSI_RED.name());

      purple.start();
      green.start();
      red.start();
    }
}


class Stopwatch {

  private TimeUnit timeUnit;

  private int i;

  public Stopwatch(TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
  }

  public void countDown() {
    countDown(5);
  }

  public void countDown(int unitCount) {

    String threadName = Thread.currentThread().getName();

    ThreadColour threadColour = ThreadColour.ANSI_RESET;

    try {
      threadColour = ThreadColour.valueOf(threadName);
    } catch (IllegalArgumentException ignore) {
      // User may pass bad colour name, ignore this err
    }
    String colour = threadColour.color();
    for (i = unitCount; i > 0; i--) {
      try {
        timeUnit.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.printf("%s%s Thread : i = %d%n", colour, threadName, i);
    }
  }

}


