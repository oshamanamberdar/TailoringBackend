package com.mdstailor.tailoringbackend.design.repository;

import com.mdstailor.tailoringbackend.design.entity.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignRepository extends JpaRepository<Design, Long> {
    Optional<Design> findDesignById(Long id);

    void deleteDesignById(Long id);
}
