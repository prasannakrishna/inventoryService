package com.bhagwat.scm.productService.entity;

import jakarta.persistence.*;

@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyId;

    @Enumerated(EnumType.STRING)
    private PartyType partyType;

    private String partyName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    // Enum for PartyType
    public enum PartyType {
        SELLER, STORE, CARRIER
    }

    // Getters and Setters
    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    // Add getters and setters for other fields
}

