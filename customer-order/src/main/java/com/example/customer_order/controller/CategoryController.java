package com.example.customer_order.controller;

import com.example.customer_order.entity.Category;
import com.example.customer_order.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/products")
    public List<Category> getCategoriesWithProducts() {
        return categoryService.getCategoriesWithProducts();
    }

    @GetMapping("/quantity-sold")
    public List<Object[]> getQuantitySoldPerCategory() {
        return categoryService.getQuantitySoldPerCategory();
    }
}