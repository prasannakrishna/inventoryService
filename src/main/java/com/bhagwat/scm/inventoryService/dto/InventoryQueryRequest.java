package com.bhagwat.scm.inventoryService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryQueryRequest {
    private String skuCode;
    private String binLocationCode;
    private Integer minQuantity;
    private Integer maxQuantity;
}
