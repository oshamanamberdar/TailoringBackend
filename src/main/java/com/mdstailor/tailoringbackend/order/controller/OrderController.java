package com.mdstailor.tailoringbackend.order.controller;

import com.mdstailor.tailoringbackend.customer.entity.Customer;
import com.mdstailor.tailoringbackend.customer.repository.CustomerRepository;
import com.mdstailor.tailoringbackend.email.EmailSenderService;
import com.mdstailor.tailoringbackend.exceptions.CustomerNotFoundException.CustomerNotFoundException;
import com.mdstailor.tailoringbackend.exceptions.OrderNotFoundException.OrderNotFoundException;
import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.order.repository.OrderRepository;
import com.mdstailor.tailoringbackend.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
  private final CustomerRepository customerRepository;
  private final OrderRepository orderRepository;
  @Autowired
  private EmailSenderService emailSenderService;


    @RequestMapping("/all")
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> orders = orderService.findAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }



    @RequestMapping("/find/{id}")
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
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderByCustomerId(@PathVariable(value = "id") Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Not found order with id" + id));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable Long id){
        return orderRepository.findById(id).map(order1 -> {
            order1.setOrderDate(order.getOrderDate());
            order1.setTrialDate(order.getTrialDate());
            order1.setDeliveryDate(order.getDeliveryDate());
            order1.setTotalAmount(order.getTotalAmount());
            order1.setAdvanceAmount(order.getAdvanceAmount());
            order1.setBalanceAmount(order.getBalanceAmount());
            order1.setItem(order.getItem());
            order1.setQuantity(order.getQuantity());
            order1.setStatus(order.getStatus());
            return orderRepository.save(order1);
        }).orElseGet(()-> {
            order.setId(id);
            return orderRepository.save(order);
        });
    }






}
