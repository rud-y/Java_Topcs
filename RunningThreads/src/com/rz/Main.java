package com.rz;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Very first Main running --");
//        Thread topThread = new Thread(() -> {
//            System.out.println("THREAD--");
//            for (int i = 0; i <= 4; i++) {
//                try {
//                    System.out.println(" 4-" + i);
//                    Thread.sleep(400);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                    }
//            });
//        topThread.start();

        System.out.println("Main Thread running.");
        try {
            System.out.println("Main Thread paused for two seconds");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            String tname = Thread.currentThread().getName();
            System.out.println(tname + " should take 7 dots to run.");
            for (int i = 0; i < 7; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
                    // State Task 1
//                    System.out.println("A. State = " + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    System.out.println("\nWhoops! .. " + tname + " interrupted!");
                    Thread.currentThread().interrupt(); // State Task 2 !!!
//                    System.out.println("A1. State = " + Thread.currentThread().getState()); // State Task 1
                    return;
                }

            }
            System.out.println("\n " + tname + " completed!");
        });

        // State Task 2
        Thread installThread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(900);
                    System.out.println("Installation Step " + (i + 1) + " is completed.");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "InstalledThread");


//
        Thread threadMonitor = new Thread(() -> {
            long now = System.currentTimeMillis();
            while(thread.isAlive()) {
                System.out.println("\nwaiting for thread to complete"); // State Task 1
                try {
                    Thread.sleep(1000);
//                System.out.println("B. State = " + thread.getState()); // State Task 1

                    if(System.currentTimeMillis() - now > 3000) {
                        thread.interrupt();
                    }
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(thread.getName() + " - starting.");
        thread.start();
        installThread.start();
        threadMonitor.start();
//
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(!thread.isInterrupted()) {
            installThread.start();
        } else {
            System.out.println("Previous thread was interrupted.! " + installThread.getName() + " cannot run!");
        }

//        System.out.println("C. State = " + thread.getState()); // State Task 1

            // Previous State check
//        System.out.println("Main thread would continue here..");
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.interrupt();

    }
}
