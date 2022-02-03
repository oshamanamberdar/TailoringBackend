package com.mdstailor.tailoringbackend.measurement.service;

import com.mdstailor.tailoringbackend.exceptions.MeasurementNotFoundException.MeasurementNotFoundException;
import com.mdstailor.tailoringbackend.measurement.entity.Measurement;
import com.mdstailor.tailoringbackend.measurement.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Measurement addMeasurement(Measurement measurement){
        return measurementRepository.save(measurement);
    }
    public List<Measurement> findAllMeasurement(){
        return measurementRepository.findAll();
    }
    public Measurement findMeasurementById(Long id){
        return measurementRepository.findMeasurementById(id).orElseThrow(()->new MeasurementNotFoundException("Measurement by id" + id + "was not found"));
    }
    public void deleteMeasurementById(Long id){
        measurementRepository.deleteMeasurementById(id);
    }
}
