package com.bhagwat.scm.inventoryService.dto;

public class PublishInventoryCommand {
    public final String inventoryId;
    public final String sellerId;

    public PublishInventoryCommand(String inventoryId, String sellerId) {
        this.inventoryId = inventoryId;
        this.sellerId = sellerId;
    }
}
