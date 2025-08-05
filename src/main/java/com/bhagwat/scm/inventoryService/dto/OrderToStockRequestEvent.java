package com.bhagwat.scm.inventoryService.dto;

public class OrderToStockRequestEvent {
    public final String orderId;
    public final String communityId;

    public OrderToStockRequestEvent(String orderId, String communityId) {
        this.orderId = orderId;
        this.communityId = communityId;
    }
}
