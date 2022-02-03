package com.mdstailor.tailoringbackend.customer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstailor.tailoringbackend.design.entity.Design;
import com.mdstailor.tailoringbackend.fabric.entity.Fabric;
import com.mdstailor.tailoringbackend.measurement.entity.Measurement;
import com.mdstailor.tailoringbackend.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Measurement measurement;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Design> design;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="fabric_table", joinColumns = {
            @JoinColumn(name = "customer_id", referencedColumnName ="id" )
    }, inverseJoinColumns = {
            @JoinColumn(name = "fabric_id", referencedColumnName = "id")
    })
    private Set<Fabric> fabrics;


}
