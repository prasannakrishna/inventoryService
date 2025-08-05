package com.bhagwat.scm.inventoryService.constant;

public enum InventoryStatus {
    AVAILABLE,       // Ready for picking/use
    ALLOCATED,       // Reserved for an outgoing order
    ON_HOLD,         // Temporarily unavailable (e.g., awaiting quality check)
    DAMAGED,         // Damaged, not usable
    QUARANTINED,     // Set aside for further inspection
    IN_TRANSIT,      // Moving between locations within or outside the warehouse
    RECEIVED,        // Just received, awaiting putaway
    PACKED,          // Packed, awaiting shipment
    SHIPPED,         // Has left the warehouse
    RETURNED         // Returned to warehouse, awaiting processing
    // Add more statuses as per your WMS workflow
}