package com.example.Agrios_Product.controller;

import com.example.Agrios_Product.Service.OrderService;
import com.example.Agrios_Product.model.Order;
import com.example.Agrios_Product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173") // Adjust if necessary
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Fetch all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Fetch orders by customer ID
    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable int customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    // Fetch orders by farmer ID
    @GetMapping("/farmer/{farmerId}")
    public List<Order> getOrdersByFarmerId(@PathVariable int farmerId) {
        return orderService.getOrdersByFarmerId(farmerId);
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestParam int customerId,
            @RequestParam int farmerId,
            @RequestBody List<Integer> productIds
    ) {
        try {
            Order order = orderService.createOrder(customerId, farmerId, productIds);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Update order status
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable int orderId,
            @RequestParam String status
    ) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

