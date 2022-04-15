package com.mdstailor.tailoringbackend.fabric.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstailor.tailoringbackend.customer.entity.Customer;
import com.mdstailor.tailoringbackend.supplier.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fabric implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shadeNumber;
    private Float quantity;
    private Float costPerMeter;
    private Float totalCost;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "supplier_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Supplier supplier;


}
