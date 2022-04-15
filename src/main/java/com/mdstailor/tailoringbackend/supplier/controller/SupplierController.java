package com.mdstailor.tailoringbackend.supplier.controller;

import com.mdstailor.tailoringbackend.customer.entity.Customer;
import com.mdstailor.tailoringbackend.supplier.Repository.SupplierRepository;
import com.mdstailor.tailoringbackend.supplier.entity.Supplier;
import com.mdstailor.tailoringbackend.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierRepository supplierRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Supplier>>getALlSupplier(){
        List <Supplier> suppliers = supplierService.findAllSupplier();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") Long id) {
        Supplier supplier = supplierService.findSupplierById(id);
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier){
        Supplier  newSupplier = supplierService.addSupplier(supplier);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("id") Long id){
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    Supplier updateSupplier(@RequestBody Supplier supplier, @PathVariable Long id) {
        return supplierRepository.findById(id).map(supplier1 -> {
            supplier1.setName(supplier.getName());
            supplier1.setPhone(supplier.getPhone());
            supplier1.setAddress(supplier.getAddress());
            supplier1.setTotalDue(supplier.getTotalDue());
            supplier1.setClearedDue(supplier.getClearedDue());
            supplier1.setLeftDue(supplier.getLeftDue());
            return supplierRepository.save(supplier1);
        }).orElseGet(()-> {
            supplier.setId(id);
            return supplierRepository.save(supplier);
        });

    }


}
