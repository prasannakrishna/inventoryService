package com.bhagwat.scm.inventoryService.event;

import com.bhagwat.scm.inventoryService.entity.Inventory;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.entity.Product;
import com.bhagwat.scm.inventoryService.entity.SKU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCreatedEvent implements Serializable {
    private Inventory inventory;
    private Product product;
    private SKU sku;
    private PackConfiguration packConfiguration;
}
