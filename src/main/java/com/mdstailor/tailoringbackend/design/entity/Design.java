package com.mdstailor.tailoringbackend.design.entity;

import com.mdstailor.tailoringbackend.customer.entity.Customer;
import com.mdstailor.tailoringbackend.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lapelSize;
    private Long numberOfButton;
    private String vent;
    private String kaajStyle;
    private String shirtCuff;
    private Long backPocketNumber;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;



}
