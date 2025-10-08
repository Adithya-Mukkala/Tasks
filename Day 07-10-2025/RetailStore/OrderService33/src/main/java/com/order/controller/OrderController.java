package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.Orderservice;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final Orderservice orderService;

    @Autowired
    public OrderController(Orderservice orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/messages")
    public List<String> getAllConsumedMessages() {
        return orderService.getConsumedMessages();
    }
}

