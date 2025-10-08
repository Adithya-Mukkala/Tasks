package com.order.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Orderservice {

    private final List<String> consumedMessages = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "${topic.inventory}", groupId = "order-service-group")
    public void consumeInventory(String message) {
        System.out.println("OrderService received message from InventoryService: " + message);
        consumedMessages.add(message);
    }

    public List<String> getConsumedMessages() {
        return consumedMessages;
    }
}

