package com.example.timesaleapp.repository;

import com.example.timesaleapp.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
