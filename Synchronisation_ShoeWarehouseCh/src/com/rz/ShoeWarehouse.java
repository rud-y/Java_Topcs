package com.rz;

import java.util.ArrayList;
import java.util.List;


//record Order(long orderId, String item, int qty) {};

public class ShoeWarehouse {
    private List<Order> shippingItems;
    public final static String[] PRODUCT_LIST = {"Running shoes", "Sandals", "Slippers", "Boots", "High Tops"};

    public ShoeWarehouse() {
        this.shippingItems = new ArrayList<>();
    }

    // receiveOrder
    public synchronized void receiveOrder(Order item) {

        while (shippingItems.size() > 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        shippingItems.add(item);
        System.out.println("Incoming item: "  + item);
        notifyAll();
    }


    // fulfillOrder
    public synchronized Order fulfillOrder() {

        while(shippingItems.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order item = shippingItems.remove(0);
        System.out.println(Thread.currentThread().getName() + " Fullfilled item: "  + item);
        notifyAll();
        return item;
    }
}
