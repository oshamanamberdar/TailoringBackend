package com.mdstailor.tailoringbackend.measurement.service;

import com.mdstailor.tailoringbackend.exceptions.MeasurementNotFoundException.MeasurementNotFoundException;
import com.mdstailor.tailoringbackend.exceptions.OrderNotFoundException.OrderNotFoundException;
import com.mdstailor.tailoringbackend.measurement.entity.Measurement;
import com.mdstailor.tailoringbackend.measurement.repository.MeasurementRepository;
import com.mdstailor.tailoringbackend.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    public List<Measurement> findAllMeasurement(){
        return measurementRepository.findAll();
    }

    public void deleteMeasurement(Long id) {
        measurementRepository.deleteMeasurementById(id);
    }
    public Measurement findMeasurementById(Long id){
        return measurementRepository.findMeasurementById(id).orElseThrow(()->
                new MeasurementNotFoundException("Measurement by id"+ id +"was not found"));
    }
}
