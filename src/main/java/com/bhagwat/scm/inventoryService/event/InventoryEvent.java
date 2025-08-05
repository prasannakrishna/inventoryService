package com.bhagwat.scm.inventoryService.event;

import com.bhagwat.scm.inventoryService.command.InventoryCommandDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    String eventType; // CREATED, UPDATED, DELETED
    InventoryCommandDTO payload;
}