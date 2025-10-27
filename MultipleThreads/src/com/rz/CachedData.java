package com.rz;
import java.util.concurrent.TimeUnit;

public class CachedData {

  private volatile boolean flag = false;

  public void toogleFlag() {
    flag = !flag;
  }

  public boolean isReady() {
    return flag;
  }


  public static void main(String[] args) {
    CachedData example = new CachedData();

    Thread writerThread = new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      example.toogleFlag();
      System.out.println("A. Flag set to " + example.isReady());
    });

    Thread readerThread = new Thread(() -> {
      while (!example.isReady()) {
        // busy-wait - until flag == true
      }
      System.out.println("B. Flag is " + example.isReady());
    });

    writerThread.start();
    readerThread.start();
  }
}
