package com.bhagwat.scm.productService.entity;

import jakarta.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "sku_id")
    private SKU sku;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Party seller;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String locationId;

    private Integer quantity;

    private Integer allocatedQuantity;

    private Double pricePerUnit;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private UnitTrackingLevel unitTrackingLevel;

    // Enum for Currency
    public enum Currency {
        INR, USD, EUR, GBP, JPY
    }

    // Enum for UnitTrackingLevel
    public enum UnitTrackingLevel {
        EACH, BOX, CASE, PALLET
    }

    // Getters and Setters
    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public SKU getSku() {
        return sku;
    }

    public void setSku(SKU sku) {
        this.sku = sku;
    }

    public Party getSeller() {
        return seller;
    }

    public void setSeller(Party seller) {
        this.seller = seller;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAllocatedQuantity() {
        return allocatedQuantity;
    }

    public void setAllocatedQuantity(Integer allocatedQuantity) {
        this.allocatedQuantity = allocatedQuantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public UnitTrackingLevel getUnitTrackingLevel() {
        return unitTrackingLevel;
    }

    public void setUnitTrackingLevel(UnitTrackingLevel unitTrackingLevel) {
        this.unitTrackingLevel = unitTrackingLevel;
    }
}

