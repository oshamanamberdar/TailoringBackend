package com.mdstailor.tailoringbackend.design.controller;

import com.mdstailor.tailoringbackend.design.entity.Design;
import com.mdstailor.tailoringbackend.design.repository.DesignRepository;
import com.mdstailor.tailoringbackend.design.service.DesignService;
import com.mdstailor.tailoringbackend.exceptions.CustomerNotFoundException.CustomerNotFoundException;
import com.mdstailor.tailoringbackend.exceptions.DesignNotFoundException.DesignNotFoundException;
import com.mdstailor.tailoringbackend.exceptions.OrderNotFoundException.OrderNotFoundException;
import com.mdstailor.tailoringbackend.order.entity.Order;
import com.mdstailor.tailoringbackend.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Design")
public class DesignController {
    private final OrderRepository orderRepository;
    private final DesignRepository designRepository;
    private  final DesignService designService;

    @PostMapping("/order/{orderId}/design")
    public ResponseEntity<Design> addDesign(@PathVariable(value = "orderId") Long orderId, @RequestBody Design design){
        Design designs = orderRepository.findOrderById(orderId).map(order -> {
            design.setOrder(order);
            return designRepository.save(design);
        }).orElseThrow(()->new OrderNotFoundException("Order by Id" + orderId + " Was not Found"));
        return new ResponseEntity<>(designs, HttpStatus.CREATED);
    }

    @RequestMapping("/all")
    public ResponseEntity<List<Design>> getAllDesign(){
       List<Design> designs = designService.findAllDesign();
       return new ResponseEntity<>(designs, HttpStatus.OK);

    }
    @RequestMapping("/order/{orderId}/design")
    public ResponseEntity<List<Design>> getAllDesignByOrderId(@PathVariable(value = "orderId")Long orderId){
        if(!orderRepository.existsById(orderId)){
            throw new OrderNotFoundException("Not found Order by id"+ orderId);
        }
        List<Design> designs= designRepository.findByOrderId(orderId);
        return new ResponseEntity<>(designs, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDesignByOrderId(@PathVariable("id") Long id){
        designService.deleteDesign(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Design> getDesignByDesignId(@PathVariable(value = "id") Long id) {
        Design design = designService.findDesignById(id);
        return new ResponseEntity<>(design, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Design updateDesignByID(@RequestBody Design design, @PathVariable Long id) {
        return designRepository.findById(id).map(design1 -> {
            design1.setBackPocketNumber(design.getBackPocketNumber());
            design1.setKaajStyle(design.getKaajStyle());
            design1.setLapelSize(design.getLapelSize());
            design1.setShirtCuff(design.getShirtCuff());
            design1.setVent(design.getVent());
            design1.setNumberOfButton(design.getNumberOfButton());
            return designRepository.save(design1);
        }).orElseGet(()-> {
            design.setId(id);
            return designRepository.save(design);
        });
    }


}
