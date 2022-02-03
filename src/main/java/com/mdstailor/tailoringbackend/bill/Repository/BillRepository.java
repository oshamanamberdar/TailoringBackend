package com.mdstailor.tailoringbackend.bill.Repository;

import com.mdstailor.tailoringbackend.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Optional<Bill> findBIllById(Long id);

    void deleteBillById(Long id);
}
