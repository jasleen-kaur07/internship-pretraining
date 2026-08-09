package com.example.customer_order.repository;

import com.example.customer_order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("""
        SELECT oi
        FROM OrderItem oi
        JOIN FETCH oi.product
        WHERE oi.order.id = :orderId
        """)
    List<OrderItem> findItemsByOrderId(Long orderId);
}