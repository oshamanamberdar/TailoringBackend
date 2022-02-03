package com.mdstailor.tailoringbackend.bill.service;

import com.mdstailor.tailoringbackend.bill.Repository.BillRepository;
import com.mdstailor.tailoringbackend.bill.entity.Bill;
import com.mdstailor.tailoringbackend.exceptions.BillNotFoundException.BillNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

   public Bill addBill(Bill bill) {
        return billRepository.save(bill);
   }
   public List<Bill> findAllBill(){
        return billRepository.findAll();
   }
   public Bill findBillById(Long id) {
        return billRepository.findBIllById(id).orElseThrow(()->new BillNotFoundException("bill by id" + id + "was not found"));
   }
   public void deleteBillById(Long id){
        billRepository.deleteBillById(id);
   }
}
