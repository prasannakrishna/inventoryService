package com.bhagwat.scm.inventoryService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long id;
    private Long partyId; // Party reference from Party table
    private String line1;
    private String line2;
    private String line3;
    private String post;
    private String city;
    private String district;
    private String state;
    private String country;
    private String pincode;
    private String contactNumber;
    private String email;

    // Constructor, Getters and Setters
}

