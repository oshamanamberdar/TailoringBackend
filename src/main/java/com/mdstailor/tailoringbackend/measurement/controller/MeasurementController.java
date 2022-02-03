package com.mdstailor.tailoringbackend.measurement.controller;

import com.mdstailor.tailoringbackend.measurement.entity.Measurement;
import com.mdstailor.tailoringbackend.measurement.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private  final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @RequestMapping("/all")
    public ResponseEntity <List<Measurement>> getAllMeasurement(){
        List<Measurement> measurements = measurementService.findAllMeasurement();
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }

    @RequestMapping("/find/{id}")
    public ResponseEntity<Measurement> getMeasurementById(@PathVariable("id") Long id){
        Measurement measurement = measurementService.findMeasurementById(id);
        return new ResponseEntity<>(measurement, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Measurement> addMeasurement(@RequestBody Measurement measurement){
        Measurement newMeasurement = measurementService.addMeasurement(measurement);
        return new ResponseEntity<>(newMeasurement, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMeasurementById(@PathVariable("id")Long id){
        measurementService.deleteMeasurementById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
