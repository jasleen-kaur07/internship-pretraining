package com.example.customer_order.repository;

import com.example.customer_order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

    List<Order> findTop3ByOrderByTotalAmountDesc();

    List<Order> findAllByOrderByTotalAmountDesc();

    @Query("""
        SELECT c.city,
               COUNT(o),
               SUM(o.totalAmount)
        FROM Order o
        JOIN o.customer c
        GROUP BY c.city
        """)
    List<Object[]> getCityWiseOrderSummary();

    @Query("""
        SELECT o
        FROM Order o
        LEFT JOIN FETCH o.items i
        LEFT JOIN FETCH i.product
        """)
    List<Order> findAllWithItemsAndProducts();

    @Query("""
        SELECT o
        FROM Order o
        WHERE o.totalAmount > (
            SELECT AVG(o2.totalAmount)
            FROM Order o2
        )
        ORDER BY o.totalAmount DESC
        """)
    Page<Order> findOrdersAboveAverage(Pageable pageable);
}