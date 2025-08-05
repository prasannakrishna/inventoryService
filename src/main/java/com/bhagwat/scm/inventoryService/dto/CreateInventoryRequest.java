package com.bhagwat.scm.inventoryService.dto;

public class CreateInventoryRequest {
    private String skuId;
    private Long sellerId;
    private Integer quantity;
    private Long storeId;

    public CreateInventoryRequest(String skuId, Long sellerId, Integer quantity, Long storeId) {
        this.skuId = skuId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.storeId = storeId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}

