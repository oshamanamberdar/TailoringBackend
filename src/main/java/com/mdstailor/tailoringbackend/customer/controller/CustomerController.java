package com.mdstailor.tailoringbackend.customer.controller;

import com.mdstailor.tailoringbackend.customer.entity.Customer;
import com.mdstailor.tailoringbackend.customer.repository.CustomerRepository;
import com.mdstailor.tailoringbackend.customer.service.CustomerService;
import com.mdstailor.tailoringbackend.profile.service.FileUploadHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("customer")
public class CustomerController {



    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final FileUploadHelper fileUploadHelper;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository, FileUploadHelper fileUploadHelper) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.fileUploadHelper = fileUploadHelper;
    }

    @RequestMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customers = customerService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping("/find/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("id") Long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateEmployee(@RequestBody Customer customer){
        Customer updateCustomer= customerService.updateCustomer(customer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        return customerRepository.findById(id).map(customer1 -> {
            customer1.setName(customer.getName());
            customer1.setPhone(customer.getPhone());
            customer1.setEmail(customer.getEmail());
            customer1.setCity(customer.getCity());
            customer1.setState(customer.getState());
            customer1.setCountry(customer.getCountry());
            return customerRepository.save(customer1);
        }).orElseGet(()-> {
            customer.setId(id);
            return customerRepository.save(customer);
        });

    }
    @GetMapping("/findByName/{phone}")
    public ResponseEntity<Customer> findCustomerByNumber(@PathVariable String phone){
        Customer customerPhone = customerRepository.findByPhone(phone);
        return new ResponseEntity<>(customerPhone, HttpStatus.OK);
    }

    @PostMapping("/profile")
    public ResponseEntity<?> uploadProfilePicture(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("customerId") String customerId) {
        Customer customer = customerService.findCustomerById(Long.parseLong(customerId));

        // upload logic

        if(file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is Empty");
        }
        boolean f = fileUploadHelper.uploadFile(file);
        if (f) {
            String path = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
            customer.setFilePath(path);
            customerService.addCustomer(customer);
        }
            return new ResponseEntity<>(HttpStatus.OK);

    }










}
