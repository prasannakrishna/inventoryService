package com.bhagwat.scm.productService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateInventoryCommand {
    @TargetAggregateIdentifier
    private final String inventoryId;
    private final String skuId;
    private final Long sellerId;
    private final Integer quantity;
    private final Long storeId;

    public CreateInventoryCommand(String inventoryId, String skuId, Long sellerId, Integer quantity, Long storeId) {
        this.inventoryId = inventoryId;
        this.skuId = skuId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.storeId = storeId;
    }

    // Getters
}

