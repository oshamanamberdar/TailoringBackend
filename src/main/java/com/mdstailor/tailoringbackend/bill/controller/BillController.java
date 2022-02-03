package com.mdstailor.tailoringbackend.bill.controller;

import com.mdstailor.tailoringbackend.bill.entity.Bill;
import com.mdstailor.tailoringbackend.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @RequestMapping("/all")
    public ResponseEntity<List<Bill>> getAllBill(){
        List<Bill> bills = billService.findAllBill();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
    @RequestMapping("/find/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable("id") Long id){
        Bill bill = billService.findBillById(id);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill){
        Bill newBill = billService.addBill(bill);
        return new ResponseEntity<>(newBill, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBilById(@PathVariable("id")Long id){
        billService.deleteBillById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
