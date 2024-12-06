package com.bhagwat.scm.productService.dto;

public class InventoryCreatedEvent {
    private final String inventoryId;
    private final String skuId;
    private final Long sellerId;
    private final Integer quantity;
    private final Long storeId;

    public InventoryCreatedEvent(String inventoryId, String skuId, Long sellerId, Integer quantity, Long storeId) {
        this.inventoryId = inventoryId;
        this.skuId = skuId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.storeId = storeId;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getSkuId() {
        return skuId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getStoreId() {
        return storeId;
    }
}

