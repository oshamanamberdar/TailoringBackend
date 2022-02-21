package com.mdstailor.tailoringbackend.order.repository;

import com.mdstailor.tailoringbackend.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteOrderById(Long id);

    Optional<Order> findOrderById(Long id);

    List<Order> findByCustomerId(Long customerId);
}
