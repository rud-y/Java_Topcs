package com.rz;

public class CustomThread extends Thread {
  @Override
  public void run() {
//    super.run(); // is not going to do anything

    for(int i=1; i<=5; i++) {
      System.out.print(" 1 ");
      try {
        Thread.sleep(700);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
