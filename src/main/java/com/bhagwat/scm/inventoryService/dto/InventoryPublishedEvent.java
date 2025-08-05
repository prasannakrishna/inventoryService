package com.bhagwat.scm.inventoryService.dto;

public class InventoryPublishedEvent {
    public final String inventoryId;
    public final String sellerId;

    public InventoryPublishedEvent(String inventoryId, String sellerId) {
        this.inventoryId = inventoryId;
        this.sellerId = sellerId;
    }
}
