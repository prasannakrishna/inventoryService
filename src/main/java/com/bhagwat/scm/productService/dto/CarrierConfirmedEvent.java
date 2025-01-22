package com.bhagwat.scm.productService.dto;

public class CarrierConfirmedEvent {
    public final String shipmentId;
    public final String carrierId;

    public CarrierConfirmedEvent(String shipmentId, String carrierId) {
        this.shipmentId = shipmentId;
        this.carrierId = carrierId;
    }
}
