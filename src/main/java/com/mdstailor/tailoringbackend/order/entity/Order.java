package com.mdstailor.tailoringbackend.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstailor.tailoringbackend.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_PLACED")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    private Date trialDate;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;


    private byte[] design;

    private Long totalAmount;
    private Long advanceAmount;
    private Long BalanceAmount;
    private String status;
    private String item;
    private String quantity;
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

}
