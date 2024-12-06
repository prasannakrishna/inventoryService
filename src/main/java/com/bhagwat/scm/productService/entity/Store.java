package com.bhagwat.scm.productService.entity;

import jakarta.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String storeName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String mobile;
    private String email;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Party owner;

    // Getters and Setters
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    // Add getters and setters for other fields
}

