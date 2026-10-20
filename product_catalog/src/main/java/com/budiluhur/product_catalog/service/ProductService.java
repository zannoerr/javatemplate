package com.budiluhur.product_catalog.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.budiluhur.product_catalog.model.product;
import com.budiluhur.product_catalog.repository.ProductRepository;
import com.budiluhur.product_catalog.exception.ProductNotFoundException;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    // Tambahkan variabel baru untuk DiscountService
    private final DiscountService discountService; 

    // Instruksi 3: Inject DiscountService menggunakan Constructor Injection
    public ProductService(ProductRepository productRepository, DiscountService discountService) {
        this.productRepository = productRepository;
        this.discountService = discountService;
    }

    public List<product> getAllProducts() {
        return productRepository.findAll();
    }

    public product getProductById(String id) throws ProductNotFoundException {
        return productRepository.findById(id);
    }

    public product createProduct(product product) {
        productRepository.addProduct(product);
        return product;
    }

    // Instruksi 4: Buat method baru untuk mendapatkan harga setelah didiskon berdasarkan ID
    public double getDiscountedProductPrice(String id, double discount) throws ProductNotFoundException {
        // 1. Cari produk berdasarkan ID terlebih dahulu
        product targetProduct = productRepository.findById(id);
        
        // 2. Hitung harga diskon menggunakan bantuan dari objek discountService hasil Injection
        return discountService.calculateDiscountedPrice(targetProduct.getPrice(), discount);
    }
}
