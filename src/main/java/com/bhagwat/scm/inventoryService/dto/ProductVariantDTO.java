package com.bhagwat.scm.inventoryService.dto;

import lombok.Data;

@Data
public class ProductVariantDTO {
    private Long variantId;
    private boolean isInStock;
    // add other fields as per ProductVariant entity
}