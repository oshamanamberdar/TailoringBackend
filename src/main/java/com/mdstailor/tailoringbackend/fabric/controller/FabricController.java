package com.mdstailor.tailoringbackend.fabric.controller;

import com.mdstailor.tailoringbackend.exceptions.CustomerNotFoundException.CustomerNotFoundException;
import com.mdstailor.tailoringbackend.exceptions.SupplierNotFoundException.SupplierNotFoundException;
import com.mdstailor.tailoringbackend.fabric.entity.Fabric;
import com.mdstailor.tailoringbackend.fabric.repository.FabricRepository;
import com.mdstailor.tailoringbackend.fabric.service.FabricService;
import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.supplier.Repository.SupplierRepository;
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
    private final SupplierRepository supplierRepository;

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

    @PostMapping("/supplier/{supplierId}/fabric")
    public ResponseEntity<Fabric> addFabric(@PathVariable(value = "supplierId") Long supplierId, @RequestBody Fabric fabric){
        Fabric fabrics = supplierRepository.findSupplierById(supplierId).map(supplier -> {
            fabric.setSupplier(supplier);
            return fabricRepository.save(fabric);
        }).orElseThrow(()-> new SupplierNotFoundException("Supplier by id" + supplierId));
        return new ResponseEntity<>(fabrics, HttpStatus.CREATED);
    }

    @RequestMapping("/supplier/{supplierId}/fabric")
    public ResponseEntity<List<Fabric>> getAllFabricBySupplierId(@PathVariable(value = "supplierId")Long supplierId){
        if(!supplierRepository.existsById(supplierId)){
            throw new SupplierNotFoundException("Not found Supplier by id"+ supplierId);
        }
        List<Fabric> fabrics = fabricRepository.findBySupplierId(supplierId);
        return new ResponseEntity<>(fabrics, HttpStatus.OK);
    }

}
