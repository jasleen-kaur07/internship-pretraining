package com.example.customer_order.service;

import com.example.customer_order.entity.Category;
import com.example.customer_order.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategoriesWithProducts() {
        return categoryRepository.findAllWithProducts();
    }

    public List<Object[]> getQuantitySoldPerCategory() {
        return categoryRepository.getQuantitySoldPerCategory();
    }
}