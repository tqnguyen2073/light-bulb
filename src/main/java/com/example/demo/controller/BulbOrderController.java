package com.example.demo.controller;

import com.example.demo.entity.BulbOrder;
import com.example.demo.service.BulbOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class BulbOrderController {

    private final BulbOrderService orderService;

    public BulbOrderController(BulbOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/bulb/{bulbId}")
    public ResponseEntity<BulbOrder> createOrder(@PathVariable Long bulbId, @RequestBody BulbOrder order) {
        BulbOrder createdOrder = orderService.createOrder(bulbId, order);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<BulbOrder>> getAllOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
}
