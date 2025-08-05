package com.bhagwat.scm.inventoryService.dto;

public class UpdateInventoryRequest {
    private String inventoryId;
    private Integer newQuantity;

    public UpdateInventoryRequest(String inventoryId, Integer newQuantity) {
        this.inventoryId = inventoryId;
        this.newQuantity = newQuantity;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(Integer newQuantity) {
        this.newQuantity = newQuantity;
    }
}

