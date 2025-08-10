package com.bhagwat.scm.inventoryService.event;

import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.entity.SKU;
import lombok.Data;

@Data
public class SkuUpdatedEvent {
    private SKU sku;
    private PackConfiguration packingConfiguration;
}
