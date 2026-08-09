package com.example.customer_order.service;

import com.example.customer_order.entity.Product;
import com.example.customer_order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductsWithCategories() {
        return productRepository.findAllWithCategories();
    }

    public List<Object[]> getRevenuePerProduct() {
        return productRepository.getRevenuePerProduct();
    }
}