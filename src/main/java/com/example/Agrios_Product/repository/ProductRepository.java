package com.example.Agrios_Product.repository;

import com.example.Agrios_Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.pName = :pName")
    Product findByPName(@Param("pName") String pName);

    @Query("UPDATE Product p SET p.pName = :pName, p.pPrice = :pPrice, p.pQuantity = :pQuantity, p.pDescription = :pDescription, p.pImage = :pImage WHERE p.pName = :pName")
    int updateProductByPName(@Param("pName") String pName,
                             @Param("pPrice") String pPrice,
                             @Param("pQuantity") String pQuantity,
                             @Param("pDescription") String pDescription,
                             @Param("pImage") String pImage);
    @Modifying
    @Query("DELETE FROM Product p WHERE p.pName = :pName")
    int deleteByPname(@Param("pName") String pName);
}
