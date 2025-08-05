package com.bhagwat.scm.inventoryService.dto;

public class CarrierBookingRequestEvent {
    public final String shipmentId;
    public final String source;
    public final String destination;
    public final int volume;

    public CarrierBookingRequestEvent(String shipmentId, String source, String destination, int volume) {
        this.shipmentId = shipmentId;
        this.source = source;
        this.destination = destination;
        this.volume = volume;
    }
}
