package com.mdstailor.tailoringbackend.design.service;

import com.mdstailor.tailoringbackend.design.entity.Design;
import com.mdstailor.tailoringbackend.design.repository.DesignRepository;
import com.mdstailor.tailoringbackend.exceptions.DesignNotFoundException.DesignNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DesignService {
    private final DesignRepository designRepository;

    public List<Design> findAllDesign() {
        return designRepository.findAll();
    }

    public void deleteDesign(Long id) {
        designRepository.deleteDesignById(id);
    }

    public Design findDesignById(Long id) {
        return designRepository.findDesignById(id).orElseThrow(()->
                new DesignNotFoundException("Design by id" + id + " was not found"));
    }


}
