package com.bhagwat.scm.inventoryService.command;

import com.bhagwat.scm.inventoryService.constant.InventoryStatus;
import com.bhagwat.scm.inventoryService.dto.ProductDTO;
import com.bhagwat.scm.inventoryService.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCommandDTO {
    Long inventoryId;
    String locationId;
    Integer quantity;
    Integer allocatedQuantity;
    Double pricePerUnit;
    Inventory.Currency currency;
    Inventory.InventoryType inventoryType;
    InventoryStatus inventoryStatus;
    LocalDate expiryDate;
    String lotNumber;
    String batchNumber;
    String serialNumber;
    String containerId;
    String shipmentId;
    String siteId;
    String orderId;
    String tagId;
    String receiptId;
    String origin;
    String inventoryCode;
    ProductDTO product; // includes SKU + PackingConfig
}
