package com.example.Agrios_Product.Service;


import com.example.Agrios_Product.model.Product;
import com.example.Agrios_Product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product findByPName(String pName) {
        return productRepository.findByPName(pName);
    }

    public Product updateProductByPName(String pName, Product productDetails) {
        Product existingProduct = productRepository.findByPName(pName);
        if (existingProduct != null) {
            existingProduct.setPName(productDetails.getPName());
            existingProduct.setPPrice(productDetails.getPPrice());
            existingProduct.setPQuantity(productDetails.getPQuantity());
            existingProduct.setPDescription(productDetails.getPDescription());
            existingProduct.setPImage(productDetails.getPImage());
            productRepository.save(existingProduct);
            return existingProduct;
        } else {
            return null; // Product not found
        }
    }

    @Transactional
    public boolean deleteProductByName(String pName) {
        int deletedCount = productRepository.deleteByPname(pName);
        return deletedCount > 0;
    }
}