package com.order.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class InventoryController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.Inventory}")
    private String inventoryTopic;

    @PostMapping("/create")
    public String createOrder(@RequestBody Map<String, String> orderDetails) {
        kafkaTemplate.send(inventoryTopic, orderDetails.get("text"));
        return "Order placed Successfully";
    }
}