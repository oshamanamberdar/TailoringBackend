package com.mdstailor.tailoringbackend.fabric.controller;

import com.mdstailor.tailoringbackend.fabric.entity.Fabric;
import com.mdstailor.tailoringbackend.fabric.repository.FabricRepository;
import com.mdstailor.tailoringbackend.fabric.service.FabricService;
import com.mdstailor.tailoringbackend.supplier.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/fabric")
public class FabricController {
    private final FabricService fabricService;
    private final FabricRepository fabricRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Fabric>>getAllFabrics(){
        List<Fabric> fabrics = fabricService.findAllFabric();
        return new ResponseEntity<>(fabrics, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Fabric> addFabric(@RequestBody Fabric fabric){
        Fabric newFabric = fabricService.addFabric(fabric);
        return new ResponseEntity<>(newFabric, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Fabric> findFabricById(@PathVariable("id") Long id){
        Fabric fabric = fabricService.findFabricById(id);
        return new ResponseEntity<>(fabric, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFabricById(@PathVariable("id") Long id){
        fabricService.deleteFabric(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    Fabric updateFabric(@RequestBody Fabric fabric, @PathVariable Long id) {
        return fabricRepository.findById(id).map(fabric1 -> {
            fabric1.setName(fabric.getName());
            fabric1.setColor(fabric.getColor());
            fabric1.setCostPrice(fabric.getCostPrice());
            fabric1.setQuantity(fabric.getQuantity());
            return fabricRepository.save(fabric1);
        }).orElseGet(()-> {
            fabric.setId(id);
            return fabricRepository.save(fabric);
        });

    }
}
