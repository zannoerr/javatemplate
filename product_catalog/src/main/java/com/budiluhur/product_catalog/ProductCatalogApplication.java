package com.budiluhur.product_catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.budiluhur.product_catalog.model.product;
import com.budiluhur.product_catalog.service.ProductService;
import com.budiluhur.product_catalog.exception.ProductNotFoundException;

@SpringBootApplication
public class ProductCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductService productService) {
        return args -> {
            System.out.println("=== SPRING BOOT PRODUCT CATALOG IS RUNNING ===");
            
            // 1. Tambah data baru PRD-03 ke sistem
            productService.createProduct(new product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));

            // 2. Tampilkan semua daftar produk standar di Container
            System.out.println("Daftar Produk di Spring Container:");
            productService.getAllProducts().forEach(System.out::println);

            // 3. Modifikasi Tampilan Sesuai Request Anda Bray
            System.out.println("--- INFORMASI HARGA & DISKON ---");
            
            // Kita loop semua produk yang ada di sistem
            for (product p : productService.getAllProducts()) {
                System.out.println(p); // Mencetak format [PRD-XX] Nama Produk
                
                // Menggunakan String.format untuk merapikan desimal (,00) uang rupiah bray
                String hargaNormalFormatted = String.format("%,.2f", p.getPrice()).replace(",", ".");
                System.out.println("- Harga Normal : Rp" + hargaNormalFormatted);
                
                // Menentukan besaran diskon: Contoh PRD-01 dikasih 10%, sisanya 0%
                double diskonPersen = 0.0;
                String statusDiskon = "(Tidak Ada Diskon)";
                
                if (p.getId().equalsIgnoreCase("PRD-01")) {
                    diskonPersen = 10.0;
                    statusDiskon = "(Ada Diskon)";
                }
                
                // Hitung harga akhir lewat ProductService hasil kalkulasi DiscountService bray
                double hargaAkhir = productService.getDiscountedProductPrice(p.getId(), diskonPersen);
                String hargaAkhirFormatted = String.format("%,.2f", hargaAkhir).replace(",", ".");
                
                // Tampilkan info diskon dan harga akhirnya bray
                if (diskonPersen > 0) {
                    System.out.println("- Diskon       : " + diskonPersen + "% " + statusDiskon);
                } else {
                    System.out.println("- Diskon       : 0% " + statusDiskon);
                }
                System.out.println("- Harga Akhir  : Rp" + hargaAkhirFormatted);
            }
        };
    }
}
