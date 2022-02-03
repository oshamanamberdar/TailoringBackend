package com.mdstailor.tailoringbackend.supplier.Repository;

import com.mdstailor.tailoringbackend.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    void deleteSupplierById(Long id);

    Optional<Supplier> findSupplierById(Long id);
}
