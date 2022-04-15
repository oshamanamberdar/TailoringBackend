package com.mdstailor.tailoringbackend.design.repository;

import com.mdstailor.tailoringbackend.design.entity.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DesignRepository extends JpaRepository<Design, Long> {


    void deleteDesignById(Long id);

    Optional<Design> findDesignById(Long id);

    List<Design> findByOrderId(Long orderId);
}
