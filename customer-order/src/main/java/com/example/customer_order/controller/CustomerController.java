package com.example.customer_order.controller;

import com.example.customer_order.entity.Customer;
import com.example.customer_order.entity.Order;
import com.example.customer_order.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/referrers")
    public List<Customer> getCustomersWithReferrer() {
        return customerService.getCustomersWithReferrer();
    }

    @GetMapping("/{customerId}/orders")
    public Page<Order> getOrdersByCustomer(
            @PathVariable Long customerId,
            Pageable pageable) {
        return customerService.getOrdersByCustomer(customerId, pageable);
    }

    @GetMapping("/city-summary")
    public List<Object[]> getCityWiseOrderSummary() {
        return customerService.getCityWiseOrderSummary();
    }

    @GetMapping("/top-orders")
    public List<Order> getTop3Orders() {
        return customerService.getTop3Orders();
    }

    @GetMapping("/zero-orders")
    public List<Customer> getCustomersWithZeroOrders() {
        return customerService.getCustomersWithZeroOrders();
    }

    @GetMapping("/average-order-value")
    public List<Object[]> getAverageOrderValueByCity() {
        return customerService.getAverageOrderValueByCity();
    }

    @GetMapping("/orders-above-average")
    public Page<Order> getOrdersAboveAverage(Pageable pageable) {
        return customerService.getOrdersAboveAverage(pageable);
    }

    @GetMapping("/orders-with-items")
    public List<Order> getOrdersWithItemsAndProducts() {
        return customerService.getOrdersWithItemsAndProducts();
    }
}