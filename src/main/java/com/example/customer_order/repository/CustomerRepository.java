package com.example.customer_order.repository;

import com.example.customer_order.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
        SELECT c
        FROM Customer c
        LEFT JOIN FETCH c.referredBy
        """)
    List<Customer> findAllWithReferrer();

    @Query("""
        SELECT c
        FROM Customer c
        LEFT JOIN c.orders o
        WHERE o.id IS NULL
        """)
    List<Customer> findCustomersWithZeroOrders();

    @Query("""
        SELECT c.city, AVG(o.totalAmount)
        FROM Customer c
        JOIN c.orders o
        WHERE c.isActive = true
        GROUP BY c.city
        """)
    List<Object[]> getAverageOrderValueByCity();
}