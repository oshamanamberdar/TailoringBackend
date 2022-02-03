package com.mdstailor.tailoringbackend.order.service;

import com.mdstailor.tailoringbackend.exceptions.OrderNotFoundException.OrderNotFoundException;
import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }
    public List<Order> findAllOrder(){
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteOrderById(id);
    }
    public Order findOrderById(Long id){
        return orderRepository.findOrderById(id).orElseThrow(()->
                new OrderNotFoundException("Order by id"+ id +"was not found"));
    }

}
