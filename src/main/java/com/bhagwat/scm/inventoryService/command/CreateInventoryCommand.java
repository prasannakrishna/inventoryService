package com.bhagwat.scm.inventoryService.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class CreateInventoryCommand {
    private final String inventoryId;
    private final String skuId;
    private final Long sellerId;
    private final Integer quantity;
    private final Long storeId;
}
