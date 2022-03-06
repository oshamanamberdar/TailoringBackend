package com.mdstailor.tailoringbackend.customer.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdstailor.tailoringbackend.fabric.entity.Fabric;
import com.mdstailor.tailoringbackend.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;



@Data
@Entity
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String city;
    private String state;
    private String country;
    private byte[] profileImage;

    public Customer() {
        super();
    }

    public Customer(Long id, String name, String phone, String email, String city, String state, String country, byte[] profileImage) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.state = state;
        this.country = country;
        this.profileImage = profileImage;
    }

    public Customer(String originalFilename, String contentType, byte[] compressBytes) {
    }

    public Customer(byte[] decompressBytes) {
    }
}
