package com.bhagwat.scm.inventoryService.mapper;

import com.bhagwat.scm.inventoryService.command.InventoryCommandDTO;
import com.bhagwat.scm.inventoryService.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryCommandDTO dto) {
        Inventory inv = new Inventory();
        inv.setLocationId(dto.getLocationId());
        inv.setQuantity(dto.getQuantity());
        inv.setAllocatedQuantity(dto.getAllocatedQuantity());
        inv.setPricePerUnit(dto.getPricePerUnit());
        inv.setCurrency(dto.getCurrency());
        inv.setInventoryType(dto.getInventoryType());
        inv.setInventoryStatus(dto.getInventoryStatus());
        inv.setExpiryDate(dto.getExpiryDate());
        // map Product, SKU, PackingConfiguration similarly
        return inv;
    }

    public InventoryCommandDTO toDto(Inventory entity) {
        return new InventoryCommandDTO(
                entity.getInventoryId(),
                entity.getLocationId(),
                entity.getQuantity(),
                entity.getAllocatedQuantity(),
                entity.getPricePerUnit(),
                entity.getCurrency(),
                entity.getInventoryType(),
                entity.getInventoryStatus(),
                entity.getExpiryDate(),
                entity.getLotNumber(),
                entity.getBatchNumber(),
                entity.getSerialNumber(),
                entity.getContainerId(),
                entity.getShipmentId(),
                entity.getSiteId(),
                entity.getOrderId(),
                entity.getTagId(),
                entity.getReceiptId(),
                entity.getOrigin(),
                entity.getInventoryCode(),
                null // map Product if required
        );
    }
}
