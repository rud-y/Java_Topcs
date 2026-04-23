package com.rz;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {
    private List<Order> shippingItems;

    private final ExecutorService fulfillmentService;

    public final static String[] PRODUCT_LIST = {"Running shoes", "Sandals", "Slippers", "Boots", "High Tops"};
//    public final static List<String> PRODUCT_LIST = List.of("Running shoes", "Sandals", "Slippers", "Boots", "High Tops");

    public ShoeWarehouse() {
        this.shippingItems = new ArrayList<>();
        fulfillmentService = Executors.newFixedThreadPool(3);
    }

    public void shutDown() {
        fulfillmentService.shutdown();
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
        // See which thread is processing which task
        System.out.println(Thread.currentThread().getName() + " Incoming item: "  + item);
        fulfillmentService.submit(this::fulfillOrder);
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
