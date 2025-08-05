package com.bhagwat.scm.inventoryService.dto;

public class ShipmentCreatedEvent {
    public final String shipmentId;
    public final String inventoryId;
    public final String sellerId;

    public ShipmentCreatedEvent(String shipmentId, String inventoryId, String sellerId) {
        this.shipmentId = shipmentId;
        this.inventoryId = inventoryId;
        this.sellerId = sellerId;
    }
}
