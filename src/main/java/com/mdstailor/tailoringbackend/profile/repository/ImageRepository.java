package com.mdstailor.tailoringbackend.profile.repository;

import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.profile.entity.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ImageRepository extends JpaRepository<ImageModel, Long> {


    List<ImageModel> findByCustomerId(Long customerId);
}
