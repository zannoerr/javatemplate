package com.budiluhur.product_catalog.repository; // SESUAI LOG ERROR ANDA

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.budiluhur.product_catalog.model.product; // IMPORT MODEL ANDA
import com.budiluhur.product_catalog.exception.ProductNotFoundException; // IMPORT EXCEPTION ANDA

@Repository
public class ProductRepository {
    
    private final List<product> productList = new ArrayList<>();
    
    public ProductRepository() {
        // Mengisi data awal sesuai standar PPT Slide 22
        productList.add(new product("PRD-01", "Keyboard Mechanical", 450000.0));
        productList.add(new product("PRD-02", "Mouse Wireless Silent", 175000.0));
    }
    
    public void addProduct(product product) {
        productList.add(product);
    }
    
    public List<product> findAll() {
        return productList;
    }
    
    public product findById(String id) throws ProductNotFoundException {
        for (product p : productList) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Produk dengan ID '" + id + "' tidak ditemukan!");
    }
}
