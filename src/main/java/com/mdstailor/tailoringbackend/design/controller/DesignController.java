package com.mdstailor.tailoringbackend.design.controller;

import com.mdstailor.tailoringbackend.design.entity.Design;
import com.mdstailor.tailoringbackend.design.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/design")
public class DesignController {

    private final DesignService designService;

    @Autowired
    public DesignController(DesignService designService) {
        this.designService = designService;
    }

    @RequestMapping("/all")
    public ResponseEntity<List<Design>> getAllDesign(){
        List<Design> designs = designService.findAllDesign();
        return new ResponseEntity<>(designs, HttpStatus.OK);
    }

    @RequestMapping("/find/{id}")
    public ResponseEntity<Design> getDesignById(@PathVariable ("id") Long id){
        Design design = designService.findDesignById(id);
        return new ResponseEntity<>(design, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Design> addDesign(@RequestBody Design design){
        Design newDesign  = designService.addDesign(design);
        return new ResponseEntity<>(newDesign, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDesignById(@PathVariable("id") Long id){
        designService.deleteDesignById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
