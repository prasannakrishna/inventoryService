package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.dto.SkuDTO;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.entity.SKU;
import com.bhagwat.scm.inventoryService.event.SkuCreatedEvent;
import com.bhagwat.scm.inventoryService.repository.PackingConfigurationRepository;
import com.bhagwat.scm.inventoryService.repository.SkuRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SkuCommandService {
    private final SkuRepository skuRepository;
    private final PackingConfigurationRepository packConfigurationRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;


    /**
     * Creates a new SKU in the PostgreSQL database and then publishes a Kafka event
     * to trigger an update in the MongoDB query database.
     *
     * @param skuDto The DTO containing SKU details and the pack configuration ID.
     * @return The created SKU entity.
     */
    public SKU createSku(SkuDTO skuDto) {
        //log.info("Creating SKU with name: {} and packConfigId: {}", skuDto.getSkuName(), skuDto.getPackConfigId());

        // 1. Find the PackConfiguration entity
        PackConfiguration packConfiguration = packConfigurationRepository.findById(skuDto.getPackConfigId())
                .orElseThrow(() -> new ResourceNotFoundException("PackConfiguration not found with id: " + skuDto.getPackConfigId()));

        // 2. Create and save the SKU entity in PostgreSQL
        SKU sku = new SKU();
        // You would typically use a mapper (like MapStruct) here to map fields from DTO to Entity
        sku.setSkuName(skuDto.getSkuName());
        sku.setProductGroup(skuDto.getProductGroup());
        sku.setLength(skuDto.getLength());
        sku.setWidth(skuDto.getWidth());
        sku.setHeight(skuDto.getHeight());
        sku.setWeight(skuDto.getWeight());
        sku.setIsHazardous(skuDto.getIsHazardous());
        sku.setTrackingLevel(skuDto.getTrackingLevel());
        sku.setIsSellerSKU(skuDto.getIsSellerSKU());
        sku.setPackingConfiguration(packConfiguration); // Set the full PackConfiguration
        sku.setSellerId(null); // Assuming Party entity is linked here
        sku.setUomWeight(skuDto.getUomWeight());
        sku.setUomVolume(skuDto.getUomVolume());
        sku.setUomLength(skuDto.getUomLength());
        sku.setUomDimension(skuDto.getUomDimension());
        sku.setSkuState(skuDto.getSkuState());
        sku.setPacking_tracking_level(skuDto.getPacking_tracking_level());
        sku.setShipping_tracking_level(skuDto.getShipping_tracking_level());

        SKU savedSku = skuRepository.save(sku);
        //log.info("SKU created successfully in PostgreSQL with ID: {}", savedSku.getSkuId());

        // 3. Publish a Kafka event
        SkuCreatedEvent event = new SkuCreatedEvent(savedSku, packConfiguration);
        kafkaTemplate.send("sku-created-topic", event);
        //log.info("Published SkuCreatedEvent to Kafka topic 'sku-created-topic'");

        return savedSku;
    }
}
