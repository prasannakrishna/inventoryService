package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.entity.SKU;
import com.bhagwat.scm.inventoryService.entity.SKUDocument;
import com.bhagwat.scm.inventoryService.event.SkuCreatedEvent;
import com.bhagwat.scm.inventoryService.event.SkuDeletedEvent;
import com.bhagwat.scm.inventoryService.event.SkuUpdatedEvent;
import com.bhagwat.scm.inventoryService.repository.SkuDocumentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SkuEventConsumer {

    private final SkuDocumentRepository skuDocumentRepository;

    public SkuEventConsumer(SkuDocumentRepository skuDocumentRepository) {
        this.skuDocumentRepository = skuDocumentRepository;
    }

    /**
     * Listens for a SkuCreatedEvent and saves the new SKU document to MongoDB.
     *
     * @param event The SkuCreatedEvent payload.
     */
    @KafkaListener(topics = "sku-created-topic", groupId = "sku-mongo-updater-group")
    public void handleSkuCreatedEvent(SkuCreatedEvent event) {
        //log.info("Received SkuCreatedEvent for SKU ID: {}", event.getSku().getSkuId());

        SKU sku = event.getSku();
        PackConfiguration packConfiguration = event.getPackConfiguration();

        SKUDocument skuDocument = createSkuDocument(sku, packConfiguration);
        skuDocumentRepository.save(skuDocument);
        //log.info("SKUDocument saved successfully to MongoDB with ID: {}", skuDocument.getSkuId());
    }

    /**
     * Listens for a SkuUpdatedEvent and updates the existing SKU document in MongoDB.
     * The save() method acts as an upsert, updating the document if the ID exists.
     *
     * @param event The SkuUpdatedEvent payload.
     */
    @KafkaListener(topics = "sku-updated-topic", groupId = "sku-mongo-updater-group")
    public void handleSkuUpdatedEvent(SkuUpdatedEvent event) {
        //log.info("Received SkuUpdatedEvent for SKU ID: {}", event.getSku().getSkuId());

        SKU sku = event.getSku();
        PackConfiguration packConfiguration = event.getPackingConfiguration();

        SKUDocument skuDocument = createSkuDocument(sku, packConfiguration);
        skuDocumentRepository.save(skuDocument);
        //log.info("SKUDocument updated successfully in MongoDB with ID: {}", skuDocument.getSkuId());
    }

    /**
     * Listens for a SkuDeletedEvent and removes the corresponding SKU document from MongoDB.
     *
     * @param event The SkuDeletedEvent payload containing the SKU ID.
     */
    @KafkaListener(topics = "sku-deleted-topic", groupId = "sku-mongo-updater-group")
    public void handleSkuDeletedEvent(SkuDeletedEvent event) {
        //log.info("Received SkuDeletedEvent for SKU ID: {}", event.getSkuId());

        skuDocumentRepository.deleteById(event.getSkuId());
        //log.info("SKUDocument with ID: {} deleted successfully from MongoDB.", event.getSkuId());
    }

    /**
     * Helper method to map the SKU and PackConfiguration entities to a SKUDocument.
     *
     * @param sku               The SKU entity.
     * @param packConfiguration The PackConfiguration entity.
     * @return A new SKUDocument instance.
     */
    private SKUDocument createSkuDocument(SKU sku, PackConfiguration packConfiguration) {
        PackConfiguration packConfiguration1 = new PackConfiguration();
        SKUDocument skuDocument = new SKUDocument();
        skuDocument.setSkuId(sku.getSkuId());
        skuDocument.setSkuName(sku.getSkuName());
        skuDocument.setProductGroup(sku.getProductGroup());
        skuDocument.setLength(sku.getLength());
        skuDocument.setWidth(sku.getWidth());
        skuDocument.setHeight(sku.getHeight());
        skuDocument.setWeight(sku.getWeight());
        skuDocument.setIsHazardous(sku.getIsHazardous());
        skuDocument.setTrackingLevel(sku.getTrackingLevel());
        skuDocument.setIsSellerSKU(sku.getIsSellerSKU());
        skuDocument.setPackingConfiguration(packConfiguration1);
        if(sku.getSellerId() != null)
            skuDocument.setSellerId(Long.valueOf(sku.getSellerId()));
        skuDocument.setUomWeight(sku.getUomWeight());
        skuDocument.setUomVolume(sku.getUomVolume());
        skuDocument.setUomLength(sku.getUomLength());
        skuDocument.setUomDimension(sku.getUomDimension());
        skuDocument.setSkuState(sku.getSkuState());
        skuDocument.setPacking_tracking_level(sku.getPacking_tracking_level());
        skuDocument.setShipping_tracking_level(sku.getShipping_tracking_level());
        skuDocument.setTaggedAtLevel(packConfiguration.getTaggedAtLevel());

        return skuDocument;
    }
}