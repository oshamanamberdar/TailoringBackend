package com.mdstailor.tailoringbackend.fabric.repository;

import com.mdstailor.tailoringbackend.fabric.entity.Fabric;
import com.mdstailor.tailoringbackend.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Long> {
    void deleteFabricById(Long id);
    List<Fabric> findBySupplierId(Long supplierId);

    Optional<Fabric> findFabricById(Long id);
}
