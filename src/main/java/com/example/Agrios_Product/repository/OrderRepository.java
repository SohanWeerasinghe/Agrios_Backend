package com.example.Agrios_Product.repository;

import com.example.Agrios_Product.model.Order;
import com.example.Agrios_Product.model.Product;
import com.example.Agrios_Product.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Find all orders placed by a specific customer
    List<Order> findByCustomer(User customer);

    // Find all orders assigned to a specific farmer
    List<Order> findByFarmer(User farmer);

    // Find all orders with a specific status
    List<Order> findByStatus(String status);

    // Find all orders placed on a specific date
    List<Order> findByOrderDate(LocalDate orderDate);

    // Custom query to fetch orders containing a specific product
    @Query("SELECT o FROM Order o JOIN o.products p WHERE p = :product")
    List<Order> findOrdersByProduct(Product product);

    // Find all orders within a date range
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersByDateRange(LocalDate startDate, LocalDate endDate);
}

