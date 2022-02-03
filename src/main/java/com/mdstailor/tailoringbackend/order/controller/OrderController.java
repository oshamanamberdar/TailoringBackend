package com.mdstailor.tailoringbackend.order.controller;

import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/all")
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> orders = orderService.findAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @RequestMapping("find/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        Order order= orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order newOrder = orderService.addOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
