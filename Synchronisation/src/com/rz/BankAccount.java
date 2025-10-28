package com.rz;

public class BankAccount {

  private double balance;

  public BankAccount(double balance) {
    this.balance = balance;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {

    try {
      System.out.println("Deposit - Talking to the advisor at the bank...");
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    synchronized (this) {
      double originalBalance = balance;
      balance += amount;

      System.out.printf("Starting balance: %.0f, Deposit: (%.0f)" +
              " : New BALANCE = %.0f%n", originalBalance, amount, balance);
    }

  }

  public void withdraw(double amount) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    synchronized (this) {
      double originalBalance = balance;
      if(amount <= balance) {
        balance -= amount;
        System.out.printf("Starting balance: %.0f, Withdrawal: (%.0f)" +
                " : New BALANCE = %.0f%n", originalBalance, amount, balance);
      } else {
        System.out.printf("Starting balance: %.0f, Withdrawal: (%.0f)" +
                " : INSUFFICIENT FUNDS!", originalBalance, amount);
      }
    }

  }
}
