package com.example.Agrios_Product.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pImage;
    private String pName;
    private Double pPrice;
    private String pDescription;
    private Integer pQuantity;
    private LocalDate pDate;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders; // Orders containing this product

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPImage() {  // Change to getPImage
        return pImage;
    }

    public void setPImage(String pImage) {  // Change to setPImage
        this.pImage = pImage;
    }

    public String getPName() {  // Change to getPName
        return pName;
    }

    public void setPName(String pName) {  // Change to setPName
        this.pName = pName;
    }

    public Double getPPrice() {  // Change to getPPrice
        return pPrice;
    }

    public void setPPrice(Double pPrice) {  // Change to setPPrice
        this.pPrice = pPrice;
    }

    public String getPDescription() {  // Change to getPDescription
        return pDescription;
    }

    public void setPDescription(String pDescription) {  // Change to setPDescription
        this.pDescription = pDescription;
    }

    public Integer getPQuantity() {  // Change to getPQuantity
        return pQuantity;
    }

    public void setPQuantity(Integer pQuantity) {  // Change to setPQuantity
        this.pQuantity = pQuantity;
    }

    public LocalDate getPDate() {  // Change to getPDate
        return pDate;
    }

    public void setPDate(LocalDate pDate) {  // Change to setPDate
        this.pDate = pDate;
    }
}
