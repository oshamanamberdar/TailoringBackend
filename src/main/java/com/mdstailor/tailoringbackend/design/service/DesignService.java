package com.mdstailor.tailoringbackend.design.service;

import com.mdstailor.tailoringbackend.design.entity.Design;
import com.mdstailor.tailoringbackend.design.repository.DesignRepository;
import com.mdstailor.tailoringbackend.exceptions.DesignNotFoundException.DesignNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class DesignService {
    private final DesignRepository designRepository;

    @Autowired
    public DesignService(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    public Design addDesign(Design design){
        return designRepository.save(design);
    }

    public List<Design> findAllDesign(){
        return designRepository.findAll();
    }

    public Design findDesignById(Long id){
        return designRepository.findDesignById(id).orElseThrow(()->new DesignNotFoundException("Design by id"+ id + "was not found"));

    }

    public void deleteDesignById(Long id){
        designRepository.deleteDesignById(id);
    }

}
