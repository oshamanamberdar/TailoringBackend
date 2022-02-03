package com.mdstailor.tailoringbackend.fabric.repository;

import com.mdstailor.tailoringbackend.fabric.entity.Fabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Long> {
    void deleteFabricById(Long id);

    Optional<Fabric> findFabricById(Long id);
}
