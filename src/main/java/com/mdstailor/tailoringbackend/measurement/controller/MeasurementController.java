package com.mdstailor.tailoringbackend.measurement.controller;

import com.mdstailor.tailoringbackend.customer.repository.CustomerRepository;
import com.mdstailor.tailoringbackend.exceptions.CustomerNotFoundException.CustomerNotFoundException;
import com.mdstailor.tailoringbackend.measurement.entity.Measurement;
import com.mdstailor.tailoringbackend.measurement.repository.MeasurementRepository;
import com.mdstailor.tailoringbackend.measurement.service.MeasurementService;
import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;
   private final MeasurementRepository measurementRepository;
    private final CustomerRepository customerRepository;

    @RequestMapping("/all")
    public ResponseEntity<List<Measurement>> getAllMeasurement(){
        List<Measurement> measurements = measurementService.findAllMeasurement();
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }
    @RequestMapping("find/{id}")
    public ResponseEntity<Measurement> getMeasurementById(@PathVariable("id") Long id){
        Measurement measurement= measurementService.findMeasurementById(id);
        return new ResponseEntity<>(measurement, HttpStatus.OK);
    }
    @PostMapping("/customer/{customerId}/measurement")
    public ResponseEntity<Measurement> addMeasurement(@PathVariable(value = "customerId") Long customerId, @RequestBody Measurement measurement){
        Measurement measurements = customerRepository.findCustomerById(customerId).map(customer -> {
            measurement.setCustomer(customer);
            return measurementRepository.save(measurement);
        }).orElseThrow(()-> new CustomerNotFoundException("customer by id" + customerId));
        return new ResponseEntity<>(measurements, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMeasurementById(@PathVariable("id") Long id){
        measurementService.deleteMeasurement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/customer/{customerId}/measurement")
    public ResponseEntity<List<Measurement>> getAllMeasurementByCustomerId(@PathVariable(value = "customerId")Long customerId){
        if(!customerRepository.existsById(customerId)){
            throw new CustomerNotFoundException("Not found Customer by id"+ customerId);
        }
        List<Measurement> measurements = measurementRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }

}
