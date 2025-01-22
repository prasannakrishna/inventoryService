package com.bhagwat.scm.productService.dto;

public class TriggerFinanceWorkflowCommand {
    public final String inventoryId;
    public final String sellerId;

    public TriggerFinanceWorkflowCommand(String inventoryId, String sellerId) {
        this.inventoryId = inventoryId;
        this.sellerId = sellerId;
    }
}
