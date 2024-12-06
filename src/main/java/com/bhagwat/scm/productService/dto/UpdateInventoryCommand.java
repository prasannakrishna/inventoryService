package com.bhagwat.scm.productService.dto;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateInventoryCommand {
    @TargetAggregateIdentifier
    private final String inventoryId;
    private final Integer newQuantity;

    public UpdateInventoryCommand(String inventoryId, Integer newQuantity) {
        this.inventoryId = inventoryId;
        this.newQuantity = newQuantity;
    }

    // Getters
}
