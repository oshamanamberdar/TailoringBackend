package com.mdstailor.tailoringbackend.customer.service;

import com.mdstailor.tailoringbackend.customer.entity.Customer;
import com.mdstailor.tailoringbackend.customer.repository.CustomerRepository;
import com.mdstailor.tailoringbackend.exceptions.CustomerNotFoundException.CustomerNotFoundException;
import com.mdstailor.tailoringbackend.exceptions.fabricnotfound.FabricNotFoundException;
import com.mdstailor.tailoringbackend.fabric.entity.Fabric;
import com.mdstailor.tailoringbackend.fabric.repository.FabricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

   public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
   }

   public List<Customer> findAllCustomer(){

        return customerRepository.findAll();
   }

   public Customer findCustomerById(Long id){
        return customerRepository.findCustomerById(id).orElseThrow(()-> new CustomerNotFoundException("customer by id "+ id + "was not found"));
   }

   public void deleteCustomerById(Long id){
        customerRepository.deleteCustomerById(id);
   }

//    public void updateCustomer(Long id, Customer customer){
//        Customer myCustomer = customerRepository.findById(id);
//        myCustomer.setName();
//    }
}
