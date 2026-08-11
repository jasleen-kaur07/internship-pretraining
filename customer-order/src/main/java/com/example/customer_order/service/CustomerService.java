package com.example.customer_order.service;

import com.example.customer_order.entity.Customer;
import com.example.customer_order.entity.Order;
import com.example.customer_order.repository.CustomerRepository;
import com.example.customer_order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public List<Customer> getCustomersWithReferrer() {
        return customerRepository.findAllWithReferrer();
    }

    public Page<Order> getOrdersByCustomer(Long customerId, Pageable pageable) {
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    public List<Object[]> getCityWiseOrderSummary() {
        return orderRepository.getCityWiseOrderSummary();
    }

    public List<Order> getTop3Orders() {
        return orderRepository.findTop3ByOrderByTotalAmountDesc();
    }

    public List<Customer> getCustomersWithZeroOrders() {
        return customerRepository.findCustomersWithZeroOrders();
    }

    public List<Object[]> getAverageOrderValueByCity() {
        return customerRepository.getAverageOrderValueByCity();
    }

    public Page<Order> getOrdersAboveAverage(Pageable pageable) {
        return orderRepository.findOrdersAboveAverage(pageable);
    }

    public List<Order> getOrdersWithItemsAndProducts() {
        return orderRepository.findAllWithItemsAndProducts();
    }
}