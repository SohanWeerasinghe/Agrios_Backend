package com.example.Agrios_Product.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders") // Avoid conflict with SQL keyword 'order'
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer; // The customer who placed the order

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer; // The farmer whose products are in the order

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products; // Products in the order

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "status", nullable = false)
    private String status; // e.g., "Pending", "Completed", "Cancelled"

    @Column(name = "total_price", nullable = false)
    private double totalPrice; // Total price of the order

    @Column(name = "delivery_date")
    private LocalDate deliveryDate; // Expected or actual delivery date

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus; // e.g., "Paid", "Unpaid"

    @Column(name = "delivery_address")
    private String deliveryAddress; // Address for order delivery

    // Constructors
    public Order() {}

    public Order(User customer, User farmer, List<Product> products, LocalDate orderDate, String status, double totalPrice, String paymentStatus, String deliveryAddress, LocalDate deliveryDate) {
        this.customer = customer;
        this.farmer = farmer;
        this.products = products;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getFarmer() {
        return farmer;
    }

    public void setFarmer(User farmer) {
        this.farmer = farmer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
