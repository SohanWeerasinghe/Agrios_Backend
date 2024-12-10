package com.example.Agrios_Product.repository;


import com.example.Agrios_Product.model.Order;
import com.example.Agrios_Product.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findByAccountType(String accountType);

    // Fetch orders for a specific customer by their ID
    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
    List<Order> findOrdersByCustomerId(@Param("customerId") int customerId);

    // Fetch orders for a specific farmer by their ID
    @Query("SELECT o FROM Order o WHERE o.farmer.id = :farmerId")
    List<Order> findOrdersByFarmerId(@Param("farmerId") int farmerId);


}