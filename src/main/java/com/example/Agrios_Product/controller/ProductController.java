package com.example.Agrios_Product.controller;

import com.example.Agrios_Product.Service.ProductService;
import com.example.Agrios_Product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/products")  // Ensure the prefix matches the URL you're using in Postman
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    existingProduct.setPName(productDetails.getPName());
                    existingProduct.setPPrice(productDetails.getPPrice());
                    existingProduct.setPDescription(productDetails.getPDescription());
                    existingProduct.setPQuantity(productDetails.getPQuantity());
                    existingProduct.setPDate(productDetails.getPDate());
                    existingProduct.setPImage(productDetails.getPImage());
                    return ResponseEntity.ok(productService.saveProduct(existingProduct));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{pName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String pName) {
        Product product = productService.findByPName(pName);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PutMapping("/name/{pName}")
    public ResponseEntity<Product> updateProductByName(@PathVariable String pName, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProductByPName(pName, productDetails);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build(); // If product not found
        }
    }
    @DeleteMapping("/name/{pname}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String pname) {
        boolean deleted = productService.deleteProductByName(pname); // Assume productService handles the logic
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found if product not found
        }
    }

}