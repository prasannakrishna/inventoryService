package com.bhagwat.scm.productService.dto;

public class AllocateInventoryRequest {
    private String inventoryId;
    private String orderId;
    private Integer orderQuantity;

    public AllocateInventoryRequest(String inventoryId, String orderId, Integer orderQuantity) {
        this.inventoryId = inventoryId;
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}

