package com.example.customer_order.controller;

import com.example.customer_order.entity.Product;
import com.example.customer_order.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/categories")
    public List<Product> getProductsWithCategories() {
        return productService.getProductsWithCategories();
    }

    @GetMapping("/revenue")
    public List<Object[]> getRevenuePerProduct() {
        return productService.getRevenuePerProduct();
    }
}