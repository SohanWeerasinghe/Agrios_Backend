package com.example.Agrios_Product.Service;

import com.example.Agrios_Product.model.Order;
import com.example.Agrios_Product.model.Product;
import com.example.Agrios_Product.model.User;
import com.example.Agrios_Product.repository.OrderRepository;
import com.example.Agrios_Product.repository.UserRepository;
import com.example.Agrios_Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create a new order
    public Order createOrder(int customerId, int farmerId, List<Integer> productIds) {
        User customer = userRepository.findById(customerId).orElse(null);
        User farmer = userRepository.findById(farmerId).orElse(null);
        if (customer == null || farmer == null) {
            throw new IllegalArgumentException("Customer or Farmer not found.");
        }

        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No products found for the given IDs.");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setFarmer(farmer);
        order.setProducts(products);
        order.setOrderDate(LocalDate.now());
        order.setStatus("Pending");

        return orderRepository.save(order);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get orders by customer ID
    public List<Order> getOrdersByCustomerId(int customerId) {
        User customer = userRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found.");
        }
        return orderRepository.findByCustomer(customer);
    }

    // Get orders by farmer ID
    public List<Order> getOrdersByFarmerId(int farmerId) {
        User farmer = userRepository.findById(farmerId).orElse(null);
        if (farmer == null) {
            throw new IllegalArgumentException("Farmer not found.");
        }
        return orderRepository.findByFarmer(farmer);
    }

    // Update order status
    public Order updateOrderStatus(int orderId, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null; // Order not found
    }
}
