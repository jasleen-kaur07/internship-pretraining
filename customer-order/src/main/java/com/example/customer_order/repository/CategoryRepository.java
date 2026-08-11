package com.example.customer_order.repository;

import com.example.customer_order.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
        SELECT c
        FROM Category c
        LEFT JOIN FETCH c.products
        """)
    List<Category> findAllWithProducts();

    @Query("""
        SELECT c.name, SUM(oi.quantity)
        FROM Category c
        JOIN c.products p
        JOIN OrderItem oi ON oi.product = p
        GROUP BY c.name
        """)
    List<Object[]> getQuantitySoldPerCategory();
}