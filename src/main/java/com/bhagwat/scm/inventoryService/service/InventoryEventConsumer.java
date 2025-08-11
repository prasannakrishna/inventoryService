package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.entity.*;
import com.bhagwat.scm.inventoryService.event.InventoryCreatedEvent;
import com.bhagwat.scm.inventoryService.event.InventoryEvent;
import com.bhagwat.scm.inventoryService.event.ProductCreatedEvent;
import com.bhagwat.scm.inventoryService.repository.InventoryDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEventConsumer {

    private final InventoryDocumentRepository inventoryDocumentRepository;

    @KafkaListener(topics = "inventory-created-topic", groupId = "inventory-mongo-creator-group")
    public void handleProductCreatedEvent(InventoryCreatedEvent inventoryCreatedEvent) {

        InventoryDocument inventoryDocument = createInventoryDocument(inventoryCreatedEvent.getInventory());
        inventoryDocumentRepository.save(inventoryDocument);
    }

    private InventoryDocument createInventoryDocument(Inventory inventory) {
        InventoryDocument inventoryDocument = new InventoryDocument();
        inventoryDocument.setInventoryId(inventory.getInventoryId());

        // Populate core attributes
        inventoryDocument.setSkuId(inventory.getSku() != null ? inventory.getSku().getSkuId() : null);
        inventoryDocument.setProductId(inventory.getProduct() != null ? inventory.getProduct().getProductId() : null);
        inventoryDocument.setSellerId(inventory.getSellerId());
        inventoryDocument.setStoreId(inventory.getStoreId());
        inventoryDocument.setOwnerId(inventory.getOwnerId());
        inventoryDocument.setClientId(inventory.getClientId());
        inventoryDocument.setLocationId(inventory.getLocationId());
        inventoryDocument.setQuantity(inventory.getQuantity());
        inventoryDocument.setAllocatedQuantity(inventory.getAllocatedQuantity());
        inventoryDocument.setPricePerUnit(inventory.getPricePerUnit());
        inventoryDocument.setCurrency(inventory.getCurrency() != null ? inventory.getCurrency().name() : null);
        inventoryDocument.setUnitTrackingLevel(inventory.getUnitTrackingLevel() != null ? inventory.getUnitTrackingLevel().name() : null);

        // Populate tracking and traceability attributes
        inventoryDocument.setContainerId(inventory.getContainerId());
        inventoryDocument.setShipmentId(inventory.getShipmentId());
        inventoryDocument.setCarrierId(inventory.getCarrierId());
        inventoryDocument.setSiteId(inventory.getSiteId());
        inventoryDocument.setOrderId(inventory.getOrderId());
        inventoryDocument.setTagId(inventory.getTagId());
        inventoryDocument.setLotNumber(inventory.getLotNumber());
        inventoryDocument.setBatchNumber(inventory.getBatchNumber());
        inventoryDocument.setSerialNumber(inventory.getSerialNumber());
        inventoryDocument.setReceiptId(inventory.getReceiptId());
        inventoryDocument.setOrigin(inventory.getOrigin());
        inventoryDocument.setInventoryCode(inventory.getInventoryCode());
        inventoryDocument.setInventoryType(inventory.getInventoryType() != null ? inventory.getInventoryType().name() : null);
        inventoryDocument.setInventoryStatus(inventory.getInventoryStatus() != null ? inventory.getInventoryStatus().name() : null);
        inventoryDocument.setExpiryDate(inventory.getExpiryDate());

        // Populate auditing timestamps
        inventoryDocument.setCreatedDate(inventory.getCreatedDate());
        inventoryDocument.setLastModifiedDate(inventory.getLastModifiedDate());
        inventoryDocument.setLastStockCheckDate(inventory.getLastStockCheckDate());
        inventoryDocument.setLastCycleCountDate(inventory.getLastCycleCountDate());

        // Populate denormalized product details as requested
        inventoryDocument.setProduct(inventory.getProduct());

        inventoryDocument.setReserved_for_community_orders(inventory.isReserved_for_community_orders());
        return inventoryDocument;
    }
}
