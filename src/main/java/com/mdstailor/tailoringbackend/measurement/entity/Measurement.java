package com.mdstailor.tailoringbackend.measurement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstailor.tailoringbackend.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int coatLength;
    private int coatChest;
    private int coatWaist;
    private  int coatHip;
    private  int coatShoulder;
    private  int coatSleeveLength;
    private  int coatHalfBack;
    private  int coatNeck;
    private  int shirtLength;
    private  int shirtChest;
    private  int shirtWaist;
    private  int shirtHip;
    private  int shirtShoulder;
    private  int shirtSleeveLength;
    private  int shirtNeck;
    private  int pantLength;
    private  int pantWaist;
    private  int pantHip;
    private  int pantThigh;
    private  int pantKnee;
    private  int pantBottom;
    private  int pantCrouch;
    private  int overCoatLength;
    private  int waistCoatLength;
    private  int dauraLength;
    private  int sherwaniLength;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

}
