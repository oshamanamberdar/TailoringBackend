package com.mdstailor.tailoringbackend.measurement.repository;

import com.mdstailor.tailoringbackend.measurement.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
   Optional <Measurement> findMeasurementById(Long id);

    void deleteMeasurementById(Long id);
}
