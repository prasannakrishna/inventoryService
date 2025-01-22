package com.bhagwat.scm.productService.dto;

public class PublishInventoryCommand {
    public final String inventoryId;
    public final String sellerId;

    public PublishInventoryCommand(String inventoryId, String sellerId) {
        this.inventoryId = inventoryId;
        this.sellerId = sellerId;
    }
}
