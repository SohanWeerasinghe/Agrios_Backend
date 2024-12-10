package com.example.Agrios_Product.Service;


import com.example.Agrios_Product.model.Order;
import com.example.Agrios_Product.model.User;
import com.example.Agrios_Product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderService orderService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByAccountType(String accountType) {
        return userRepository.findByAccountType(accountType);
    }
    // Add this method to fetch orders for a customer
    public List<Order> getOrdersByCustomerId(int customerId) {
        User customer = userRepository.findById(customerId).orElse(null);
        return (customer != null && "CUSTOMER".equalsIgnoreCase(customer.getAccountType()))
                ? customer.getOrders()
                : null;
    }

    // Add this method to fetch orders for a farmer
    public List<Order> getOrdersByFarmerId(int farmerId) {
        User farmer = userRepository.findById(farmerId).orElse(null);
        if (farmer != null && "FARMER".equalsIgnoreCase(farmer.getAccountType())) {
            // Fetch orders containing products added by this farmer (to be implemented in OrderService)
            return orderService.getOrdersByFarmerId(farmerId);
        }
        return null;
    }

}

