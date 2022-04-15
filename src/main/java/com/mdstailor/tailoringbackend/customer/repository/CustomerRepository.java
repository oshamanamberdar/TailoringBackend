package com.mdstailor.tailoringbackend.customer.repository;

import com.mdstailor.tailoringbackend.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
   Optional<Customer>  findCustomerById(Long id);

    void deleteCustomerById(Long id);

}
