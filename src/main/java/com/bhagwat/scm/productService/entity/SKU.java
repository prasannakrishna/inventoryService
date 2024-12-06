package com.bhagwat.scm.productService.entity;

import jakarta.persistence.*;

@Entity
public class SKU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skuId;

    private String skuName;

    private String productGroup;
    private Double length;
    private Double width;
    private Double height;
    private Boolean isHazardous;

    @Enumerated(EnumType.STRING)
    private PackingConfiguration.TrackingLevel trackingLevel;

    @Enumerated(EnumType.STRING)
    private PackingLevel packingLevel;

    private Boolean isSellerSKU;

    @ManyToOne
    @JoinColumn(name = "pack_config_id")
    private PackingConfiguration packingConfiguration;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Party seller;

    public enum PackingLevel {
        EACH, CASE, BOX, PALLET
    }

    // Getters and Setters
    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    // Add getters and setters for other fields
}

