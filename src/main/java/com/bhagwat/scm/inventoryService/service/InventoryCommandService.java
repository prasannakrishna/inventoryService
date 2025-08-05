package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.constant.InventoryStatus;
import com.bhagwat.scm.inventoryService.dto.InventoryDto;
import com.bhagwat.scm.inventoryService.entity.*;
import com.bhagwat.scm.inventoryService.event.InventoryCreatedEvent;
import com.bhagwat.scm.inventoryService.event.InventoryDeletedEvent;
import com.bhagwat.scm.inventoryService.event.InventoryUpdatedEvent;
import com.bhagwat.scm.inventoryService.repository.InventoryRepository;
import com.bhagwat.scm.inventoryService.repository.ProductRepository;
import com.bhagwat.scm.inventoryService.repository.SkuRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class InventoryCommandService {

    private final InventoryRepository inventoryRepository;
    private final SkuRepository skuRepository;
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;


    /**
     * Creates a new Inventory item in the PostgreSQL database and then publishes a Kafka event
     * to trigger an update in the MongoDB query database.
     *
     * @param inventoryDto The DTO containing Inventory details.
     * @return The created Inventory entity.
     * @throws ResourceNotFoundException if SKU or Product is not found.
     */
    @Transactional
    public Inventory createInventory(InventoryDto inventoryDto) {
        //log.info("Creating Inventory for SKU ID: {} and Product ID: {}", inventoryDto.getSkuId(), inventoryDto.getProductId());

        SKU sku = skuRepository.findById(inventoryDto.getSkuId())
                .orElseThrow(() -> new ResourceNotFoundException("SKU not found with id: " + inventoryDto.getSkuId()));

        Product product = productRepository.findById(inventoryDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + inventoryDto.getProductId()));


        Inventory inventory = new Inventory();
        // Map DTO to Entity (consider using MapStruct here)
        inventory.setSku(sku);
        inventory.setProduct(product);
        inventory.setSellerId(inventoryDto.getSellerId());
        inventory.setStoreId(inventoryDto.getStoreId());
        inventory.setOwnerId(inventoryDto.getOwnerId());
        inventory.setClientId(inventoryDto.getClientId());
        inventory.setLocationId(inventoryDto.getLocationId());
        inventory.setQuantity(inventoryDto.getQuantity());
        inventory.setAllocatedQuantity(inventoryDto.getAllocatedQuantity());
        inventory.setPricePerUnit(inventoryDto.getPricePerUnit());
        inventory.setCurrency(Inventory.Currency.valueOf(inventoryDto.getCurrency()));
        inventory.setUnitTrackingLevel(Inventory.UnitTrackingLevel.valueOf(inventoryDto.getUnitTrackingLevel()));
        inventory.setContainerId(inventoryDto.getContainerId());
        inventory.setShipmentId(inventoryDto.getShipmentId());
        inventory.setCarrierId(inventoryDto.getCarrierId());
        inventory.setSiteId(inventoryDto.getSiteId());
        inventory.setOrderId(inventoryDto.getOrderId());
        inventory.setTagId(inventoryDto.getTagId());
        inventory.setLotNumber(inventoryDto.getLotNumber());
        inventory.setBatchNumber(inventoryDto.getBatchNumber());
        inventory.setSerialNumber(inventoryDto.getSerialNumber());
        inventory.setReceiptId(inventoryDto.getReceiptId());
        inventory.setOrigin(inventoryDto.getOrigin());
        inventory.setInventoryCode(inventoryDto.getInventoryCode());
        inventory.setInventoryType(Inventory.InventoryType.valueOf(inventoryDto.getInventoryType()));
        inventory.setInventoryStatus(InventoryStatus.valueOf(inventoryDto.getInventoryStatus()));
        inventory.setExpiryDate(inventoryDto.getExpiryDate());

        Inventory savedInventory = inventoryRepository.save(inventory);
        //log.info("Inventory created successfully in PostgreSQL with ID: {}", savedInventory.getInventoryId());

        // Get PackConfiguration from SKU
        PackConfiguration packConfiguration = sku.getPackingConfiguration();

        // Publish a Kafka event
        InventoryCreatedEvent event = new InventoryCreatedEvent(savedInventory, product, sku, packConfiguration);
        kafkaTemplate.send("inventory-created-topic", savedInventory.getInventoryId().toString(), event);
        //log.info("Published InventoryCreatedEvent for Inventory ID: {} to Kafka topic 'inventory-created-topic'", savedInventory.getInventoryId());

        return savedInventory;
    }

    /**
     * Updates an existing Inventory item in the PostgreSQL database and then publishes a Kafka event.
     *
     * @param inventoryId The ID of the Inventory item to update.
     * @param inventoryDto The DTO containing updated Inventory details.
     * @return The updated Inventory entity.
     * @throws ResourceNotFoundException if Inventory, SKU, or Product is not found.
     */
    @Transactional
    public Inventory updateInventory(Long inventoryId, InventoryDto inventoryDto) {
        //log.info("Updating Inventory with ID: {}", inventoryId);

        Inventory existingInventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + inventoryId));

        SKU sku = existingInventory.getSku();
        if (!sku.getSkuId().equals(inventoryDto.getSkuId())) {
            sku = skuRepository.findById(inventoryDto.getSkuId())
                    .orElseThrow(() -> new ResourceNotFoundException("SKU not found with id: " + inventoryDto.getSkuId()));
            existingInventory.setSku(sku);
        }

        Product product = existingInventory.getProduct();
        if (!product.getProductId().equals(inventoryDto.getProductId())) {
            product = productRepository.findById(inventoryDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + inventoryDto.getProductId()));
            existingInventory.setProduct(product);
        }

        // Update fields from DTO to existing Inventory entity
        existingInventory.setSellerId(inventoryDto.getSellerId());
        existingInventory.setStoreId(inventoryDto.getStoreId());
        existingInventory.setOwnerId(inventoryDto.getOwnerId());
        existingInventory.setClientId(inventoryDto.getClientId());
        existingInventory.setLocationId(inventoryDto.getLocationId());
        existingInventory.setQuantity(inventoryDto.getQuantity());
        existingInventory.setAllocatedQuantity(inventoryDto.getAllocatedQuantity());
        existingInventory.setPricePerUnit(inventoryDto.getPricePerUnit());
        existingInventory.setCurrency(Inventory.Currency.valueOf(inventoryDto.getCurrency()));
        existingInventory.setUnitTrackingLevel(Inventory.UnitTrackingLevel.valueOf(inventoryDto.getUnitTrackingLevel()));
        existingInventory.setContainerId(inventoryDto.getContainerId());
        existingInventory.setShipmentId(inventoryDto.getShipmentId());
        existingInventory.setCarrierId(inventoryDto.getCarrierId());
        existingInventory.setSiteId(inventoryDto.getSiteId());
        existingInventory.setOrderId(inventoryDto.getOrderId());
        existingInventory.setTagId(inventoryDto.getTagId());
        existingInventory.setLotNumber(inventoryDto.getLotNumber());
        existingInventory.setBatchNumber(inventoryDto.getBatchNumber());
        existingInventory.setSerialNumber(inventoryDto.getSerialNumber());
        existingInventory.setReceiptId(inventoryDto.getReceiptId());
        existingInventory.setOrigin(inventoryDto.getOrigin());
        existingInventory.setInventoryCode(inventoryDto.getInventoryCode());
        existingInventory.setInventoryType(Inventory.InventoryType.valueOf(inventoryDto.getInventoryType()));
        existingInventory.setInventoryStatus(InventoryStatus.valueOf(inventoryDto.getInventoryStatus()));
        existingInventory.setExpiryDate(inventoryDto.getExpiryDate());

        Inventory updatedInventory = inventoryRepository.save(existingInventory);
        //log.info("Inventory updated successfully in PostgreSQL with ID: {}", updatedInventory.getInventoryId());

        // Get PackConfiguration from SKU
        PackConfiguration packConfiguration = sku.getPackingConfiguration();

        // Publish Kafka event for update
        InventoryUpdatedEvent event = new InventoryUpdatedEvent(updatedInventory, product, sku, packConfiguration);
        kafkaTemplate.send("inventory-updated-topic", updatedInventory.getInventoryId().toString(), event);
        //log.info("Published InventoryUpdatedEvent for Inventory ID: {} to Kafka topic 'inventory-updated-topic'", updatedInventory.getInventoryId());

        return updatedInventory;
    }

    /**
     * Deletes an Inventory item from the PostgreSQL database and then publishes a Kafka event.
     *
     * @param inventoryId The ID of the Inventory item to delete.
     * @throws ResourceNotFoundException if the Inventory item is not found.
     */
    @Transactional
    public void deleteInventory(Long inventoryId) {
        //log.info("Deleting Inventory with ID: {}", inventoryId);

        if (!inventoryRepository.existsById(inventoryId)) {
            throw new ResourceNotFoundException("Inventory not found with id: " + inventoryId);
        }

        inventoryRepository.deleteById(inventoryId);
        //log.info("Inventory deleted successfully from PostgreSQL with ID: {}", inventoryId);

        // Publish Kafka event for deletion
        InventoryDeletedEvent event = new InventoryDeletedEvent(inventoryId);
        kafkaTemplate.send("inventory-deleted-topic", inventoryId.toString(), event);
        //log.info("Published InventoryDeletedEvent for Inventory ID: {} to Kafka topic 'inventory-deleted-topic'", inventoryId);
    }
}