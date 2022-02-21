package com.mdstailor.tailoringbackend.order.controller;

import com.mdstailor.tailoringbackend.customer.repository.CustomerRepository;
import com.mdstailor.tailoringbackend.exceptions.CustomerNotFoundException.CustomerNotFoundException;
import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.order.repository.OrderRepository;
import com.mdstailor.tailoringbackend.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;


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
    @PostMapping("/customer/{customerId}/order")
    public ResponseEntity<Order> addOrder(@PathVariable(value = "customerId") Long customerId, @RequestBody Order order){
       Order orders = customerRepository.findCustomerById(customerId).map(customer -> {
        order.setCustomer(customer);
        return orderRepository.save(order);
       }).orElseThrow(()-> new CustomerNotFoundException("customer by id" + customerId));
       return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping("/customer/{customerId}/order")
    public ResponseEntity<List<Order>> getAllOrderByCustomerId(@PathVariable(value = "customerId")Long customerId){
        if(!customerRepository.existsById(customerId)){
            throw new CustomerNotFoundException("Not found Customer by id"+ customerId);
        }
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
