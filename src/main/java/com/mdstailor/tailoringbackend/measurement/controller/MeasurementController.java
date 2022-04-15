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
    @RequestMapping("/find/{id}")
    public ResponseEntity<Measurement> getMeasurementById(@PathVariable("id") Long id){
        Measurement measurement= measurementService.findMeasurementById(id);
        return new ResponseEntity<>(measurement, HttpStatus.OK);
    }
    @PostMapping("/customers/{customerId}/measurement")
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

    @PutMapping("/update/{id}")
    public Measurement updateMeasurement(@RequestBody Measurement measurement, @PathVariable Long id) {
        return measurementRepository.findById(id).map(measurement1 -> {
            measurement1.setCoatLength(measurement.getCoatLength());
            measurement1.setCoatChest(measurement.getCoatChest());
            measurement1.setCoatWaist(measurement.getCoatWaist());
            measurement1.setCoatHip(measurement.getCoatHip());
            measurement1.setCoatShoulder(measurement.getCoatShoulder());
            measurement1.setCoatHalfBack(measurement.getCoatHalfBack());
            measurement1.setCoatSleeveLength(measurement.getCoatSleeveLength());
            measurement1.setCoatNeck(measurement.getCoatNeck());
            measurement1.setPantLength(measurement.getPantLength());
            measurement1.setPantWaist(measurement.getPantWaist());
            measurement1.setPantHip(measurement.getPantHip());
            measurement1.setPantThigh(measurement.getPantThigh());
            measurement1.setPantKnee(measurement.getPantKnee());
            measurement1.setPantBottom(measurement.getPantBottom());
            measurement1.setPantCrouch(measurement.getPantCrouch());
            measurement1.setShirtLength(measurement.getShirtLength());
            measurement1.setShirtChest(measurement.getShirtChest());
            measurement1.setShirtWaist(measurement.getShirtWaist());
            measurement1.setShirtHip(measurement.getShirtHip());
            measurement1.setShirtShoulder(measurement.getShirtShoulder());
            measurement1.setShirtSleeveLength(measurement.getShirtSleeveLength());
            measurement1.setShirtNeck(measurement.getShirtNeck());
            return measurementRepository.save(measurement1);
        }).orElseGet(()-> {
            measurement.setId(id);
            return measurementRepository.save(measurement);
        });
    }


}
