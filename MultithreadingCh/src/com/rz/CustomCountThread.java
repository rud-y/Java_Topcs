package com.rz;

import java.util.concurrent.ThreadLocalRandom;

// Even numbers Thread
public class CustomCountThread extends Thread {

  @Override
  public void run() {
    System.out.println("Even nums thread starting : ");

    for (int i = 0; i <= 5; i++) {
      if(i % 2 == 0) System.out.println("even Thread: " + i);

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        System.out.println("! Even thread interrupted !");
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
}
