package com.rz;

import com.rz.ShoeWarehouse;

import java.util.Random;

record Order(long orderId, String item, int qty) {};

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {

        ShoeWarehouse warehouse = new ShoeWarehouse();
        Thread producerThread = new Thread(() -> {
            for(int i = 0; i < 6; i++) {
                warehouse.receiveOrder(new Order(
                        random.nextLong(1000, 9999),
                        ShoeWarehouse.PRODUCT_LIST[random.nextInt(0, 5)],
                        random.nextInt(1, 4)));
            }
        });
        producerThread.start();

        for(int x = 0; x < 2; x++) {
            Thread consumerThread = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    Order item = warehouse.fulfillOrder();
                }
            });
            consumerThread.start();
        }

    }
}

