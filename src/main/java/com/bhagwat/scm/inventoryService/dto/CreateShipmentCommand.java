package com.bhagwat.scm.inventoryService.dto;

public class CreateShipmentCommand {
    public final String inventoryId;
    public final String sellerId;
    public final String destination;

    public CreateShipmentCommand(String inventoryId, String sellerId, String destination) {
        this.inventoryId = inventoryId;
        this.sellerId = sellerId;
        this.destination = destination;
    }
}
