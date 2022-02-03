package com.mdstailor.tailoringbackend.supplier.service;

import com.mdstailor.tailoringbackend.exceptions.SupplierNotFoundException.SupplierNotFoundException;
import com.mdstailor.tailoringbackend.supplier.Repository.SupplierRepository;
import com.mdstailor.tailoringbackend.supplier.entity.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier addSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }


    public List<Supplier> findAllSupplier(){
        return supplierRepository.findAll();
    }

    public void deleteSupplier(Long id){
        supplierRepository.deleteSupplierById(id);
    }

    public Supplier findSupplierById(Long id){
        return supplierRepository.findSupplierById(id).orElseThrow(()->
                new SupplierNotFoundException("Supplier by id" + id + "was not found"));
    }
}
