package com.rz;

public class BankAccount {

  private double balance;
  private String name;

  private final Object lockName = new Object();
  private final Object lockBalance = new Object();

  public BankAccount(String name, double balance) {
    this.balance = balance;
    this.name = name;
  }

  public double getBalance() {
    return balance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    synchronized (lockName) {
      this.name = name;
      System.out.println("Name updated: " + this.name);
    }
  }

  public void deposit(double amount) {
    try {
      System.out.println("Deposit - Talking to the advisor at the bank...");
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

//    Double boxedBalance = this.balance;

    synchronized (lockBalance) {
      double originalBalance = balance;
      balance += amount;

      System.out.printf("Starting balance: %.0f, Deposit: (%.0f)" +
              " : New BALANCE = %.0f%n", originalBalance, amount, balance);
      addPromotionalMoney(amount);
    }
  }

  private void addPromotionalMoney(double amount) {

    if(amount >= 5000) {
      synchronized (lockBalance) {
        System.out.println("Congratulations, you earned promotional deposit!");
        balance += 25;
      }
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
