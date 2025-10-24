package com.rz;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // write your code here
        var currentThread = Thread.currentThread();
        System.out.println("currentThread getClass getName: " + currentThread.getClass().getName());

        System.out.println("currentThread: " + currentThread);
        printThreadState(currentThread);

        currentThread.setName("TheDude");
        currentThread.setPriority(Thread.MAX_PRIORITY);
        printThreadState(currentThread);

        // ---CustomThread instance
        CustomThread customThread = new CustomThread();
        customThread.start(); // ASYNCHRONOUS in  main method
        //customThread.run(); // SYNCHRONOUS

        // myRunnable var using Lambda expression
        Runnable myRunnable = () -> {

            for (int i = 0; i <= 8; i++) {
                System.out.print(" 2 ");
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

            Thread myThread = new Thread(myRunnable);
            myThread.start();

            for (int i = 0; i <= 3; i++) {
                System.out.print(" 0 ");
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void printThreadState (Thread thread){

            System.out.println("-------------");
            System.out.println("Thread ID : " + thread.getId());
            System.out.println("Thread name : " + thread.getName());
            System.out.println("Thread priority : " + thread.getPriority());
            System.out.println("Thread state : " + thread.getState());
            System.out.println("Thread group : " + thread.getThreadGroup());
            System.out.println("Thred IsAlive : " + thread.isAlive());
            System.out.println("-------------");

        }
    }
