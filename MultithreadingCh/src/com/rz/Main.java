package com.rz;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    try {
      System.out.println("MAIN THREAD...");
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Runnable myOddRunnable = () -> {
      System.out.println("Odd numbers thread...");
      for (int i = 0; i <= 5; i++) {
        if (i % 2 != 0) System.out.println("odd Runnable: " +i);
        try {
          Thread.sleep(800);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    Thread oddThread = new Thread(myOddRunnable, "OddyRunnable");
    CustomCountThread evenThread = new CustomCountThread();

    evenThread.start();
    oddThread.start();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    evenThread.interrupt();

//    long nowPrevious = System.currentTimeMillis();
//    while (evenThread.isAlive()) {
//      System.out.println("...waiting for even thread " + evenThread.getName() + " to complete");
//      try {
//        Thread.sleep(500);
//        if (System.currentTimeMillis() - nowPrevious > 2000) {
//          evenThread.interrupt();
//        }
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }

//    try {
//      evenThread.join();
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//
//    if (!evenThread.isInterrupted()) {
//      oddThread.start();
//    } else {
//      System.out.println(evenThread.getName() + " was interrupted. The odd one " + oddThread.getName() +
//              " cant run right now.");
//      Thread.currentThread().interrupt();
//      return;
//    }

  }
}

