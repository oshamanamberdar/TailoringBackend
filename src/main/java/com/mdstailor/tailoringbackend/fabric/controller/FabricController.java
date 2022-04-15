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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PutMapping("/update/{id}")
    Fabric updateFabric(@RequestBody Fabric fabric, @PathVariable Long id) {
        return fabricRepository.findById(id).map(fabric1 -> {
            fabric1.setName(fabric.getName());
            fabric1.setCostPerMeter(fabric.getCostPerMeter());
            fabric1.setQuantity(fabric.getQuantity());
            fabric1.setTotalCost(fabric.getTotalCost());
            return fabricRepository.save(fabric1);
        }).orElseGet(()-> {
            fabric.setId(id);
            return fabricRepository.save(fabric);
        });

    }

    @GetMapping("/fabrics/{pageNo}/{pageSize}")
    public List<Fabric> getPaginatedFabrics(@PathVariable int pageNo,
                                            @PathVariable int pageSize) {
        return fabricService.findPaginated(pageNo,pageSize);
    }

    @GetMapping("/allFabric")
    public ResponseEntity<Map<String, Object>> getAllFabrics(
            @RequestParam(required = false) String shadeNumber,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
      try {
          List<Fabric> fabrics = new ArrayList<Fabric>();
          Pageable paging = PageRequest.of(page, size);
          Page<Fabric> fabricPage;
          if(shadeNumber == null)
              fabricPage = fabricRepository.findAll(paging);
          else
              fabricPage = fabricRepository.findByShadeNumber(shadeNumber, paging);
          fabrics = fabricPage.getContent();
          Map<String, Object> response = new HashMap<>();
          response.put("fabrics", fabrics);
          response.put("currentPage", fabricPage.getNumber());
          response.put("totalItems", fabricPage.getTotalElements());
          response.put("totalPages", fabricPage.getTotalPages());
          return new ResponseEntity<>(response, HttpStatus.OK);

      }catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }



}
