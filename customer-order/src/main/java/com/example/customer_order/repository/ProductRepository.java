package com.example.customer_order.repository;

import com.example.customer_order.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
        SELECT DISTINCT p
        FROM Product p
        LEFT JOIN FETCH p.categories
        """)
    List<Product> findAllWithCategories();

    @Query("""
        SELECT p.name, SUM(oi.quantity * oi.price)
        FROM OrderItem oi
        JOIN oi.product p
        GROUP BY p.name
        """)
    List<Object[]> getRevenuePerProduct();
}