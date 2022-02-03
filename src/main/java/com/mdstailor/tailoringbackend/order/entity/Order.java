package com.mdstailor.tailoringbackend.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstailor.tailoringbackend.bill.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_PLACED")
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    private String particulars;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Bill bills;
}
