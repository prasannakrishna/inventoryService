package com.bhagwat.scm.productService.dto;

public class FinanceWorkflowTriggeredEvent {
    public final String inventoryId;
    public final String sellerId;

    public FinanceWorkflowTriggeredEvent(String inventoryId, String sellerId) {
        this.inventoryId = inventoryId;
        this.sellerId = sellerId;
    }
}
