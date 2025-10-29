package com.rz;

public class Main {
  public static void main(String[] args) {
    BankAccount companyAccount = new BankAccount("Philip", 10000);

    Thread thread1 = new Thread(() -> companyAccount.withdraw(2500));
    Thread thread2 = new Thread(() -> companyAccount.deposit(5100));
    System.out.println("Original name: " + companyAccount.getName());
    Thread thread3= new Thread(() -> companyAccount.setName("Filip"));
    Thread thread4= new Thread(() -> companyAccount.withdraw(5000));

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    try {
      thread1.join();
      thread2.join();

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      thread3.join();
      thread4.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("FINAL BALANCE: " + companyAccount.getBalance());

  }
}
