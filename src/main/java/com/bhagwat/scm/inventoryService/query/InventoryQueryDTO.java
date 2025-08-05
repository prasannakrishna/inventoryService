package com.bhagwat.scm.inventoryService.query;

import com.bhagwat.scm.inventoryService.constant.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryQueryDTO {
    String productId;
    String skuName;
    InventoryStatus inventoryStatus;
    String siteId;
    String locationId;
    LocalDate expiryBefore;
}
