package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.dto.ProductDTO;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.entity.Product;
import com.bhagwat.scm.inventoryService.entity.SKU;
import com.bhagwat.scm.inventoryService.event.ProductCreatedEvent;
import com.bhagwat.scm.inventoryService.event.ProductDeletedEvent;
import com.bhagwat.scm.inventoryService.event.ProductUpdatedEvent;
import com.bhagwat.scm.inventoryService.mapper.ProductMapper;
import com.bhagwat.scm.inventoryService.repository.PackingConfigurationRepository;
import com.bhagwat.scm.inventoryService.repository.ProductRepository;
import com.bhagwat.scm.inventoryService.repository.SkuRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandService {

    private final ProductRepository productRepository;
    private final SkuRepository skuRepository;
    private final PackingConfigurationRepository packConfigRepository;
    private final ProductMapper productMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Product createProduct(ProductDTO productDto) {
        //Product product = productMapper.toEntity(productDto);

        // Handle nested SKU and packing configuration
        SKU sku = skuRepository.findById(productDto.getSkuId())
                .orElseThrow(() -> new ResourceNotFoundException("SKU not found with id: " + productDto.getSkuId()));

        // 2. Find the PackConfiguration associated with the SKU
        PackConfiguration packConfiguration = sku.getPackingConfiguration();
        if (packConfiguration == null) {
            throw new ResourceNotFoundException("PackConfiguration not found for SKU with id: " + productDto.getSkuId());
        }
        Product product = new Product();
        // Using MapStruct or similar would simplify this mapping
        product.setProductId(productDto.getProductId());
        product.setSellerId(productDto.getSellerId());
        product.setSku(sku); // Set the full SKU entity
        product.setProductName(productDto.getProductName());
        product.setBrand(productDto.getBrand());
        product.setDescription(productDto.getDescription());
        product.setHashKeys(productDto.getHashKeys());
        product.setSeasonal(productDto.isSeasonal());
        product.setPrice(productDto.getPrice());
        product.setMRP_Price(productDto.getMRP_Price());
        product.set_in_stock(productDto.is_in_stock());
        product.setShipping_tracking_level(productDto.getShipping_tracking_level());
        product.setFrequency(productDto.getFrequency());
        product.setStore_tracking_level(productDto.getStore_tracking_level());
        product.setCaptureExpiryDuringCreateInventory(productDto.isCaptureExpiryDuringCreateInventory());
        // inventoryLinks are managed separately or initialized by default in entity

        Product savedProduct = productRepository.save(product);
        //log.info("Product created successfully in PostgreSQL with ID: {}", savedProduct.getProductId());

        // 4. Publish a Kafka event
        ProductCreatedEvent event = new ProductCreatedEvent(savedProduct, sku, packConfiguration);
        kafkaTemplate.send("product-created-topic", savedProduct.getProductId(), event); // Key by productId
        //log.info("Published ProductCreatedEvent for Product ID: {} to Kafka topic 'product-created-topic'", savedProduct.getProductId());

        return savedProduct;
    }

    @Transactional
    public Product updateProduct(String productId, ProductDTO productDto) {
        //log.info("Updating Product with ID: {}", productId);

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        // If SKU ID is changed, fetch the new SKU and its PackConfiguration
        SKU sku;
        PackConfiguration packConfiguration;
        if (!existingProduct.getSku().getSkuId().equals(productDto.getSkuId())) {
            sku = skuRepository.findById(productDto.getSkuId())
                    .orElseThrow(() -> new ResourceNotFoundException("SKU not found with id: " + productDto.getSkuId()));
            packConfiguration = sku.getPackingConfiguration();
            if (packConfiguration == null) {
                throw new ResourceNotFoundException("PackConfiguration not found for SKU with id: " + productDto.getSkuId());
            }
            existingProduct.setSku(sku);
        } else {
            sku = existingProduct.getSku();
            packConfiguration = sku.getPackingConfiguration();
        }

        // Update fields from DTO to existing Product entity
        existingProduct.setSellerId(productDto.getSellerId());
        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setHashKeys(productDto.getHashKeys());
        existingProduct.setSeasonal(productDto.isSeasonal());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setMRP_Price(productDto.getMRP_Price());
        existingProduct.set_in_stock(productDto.is_in_stock());
        existingProduct.setShipping_tracking_level(productDto.getShipping_tracking_level());
        existingProduct.setFrequency(productDto.getFrequency());
        existingProduct.setStore_tracking_level(productDto.getStore_tracking_level());
        existingProduct.setCaptureExpiryDuringCreateInventory(productDto.isCaptureExpiryDuringCreateInventory());

        Product updatedProduct = productRepository.save(existingProduct);
        //log.info("Product updated successfully in PostgreSQL with ID: {}", updatedProduct.getProductId());

        // Publish Kafka event for update
        ProductUpdatedEvent event = new ProductUpdatedEvent(updatedProduct, sku, packConfiguration);
        kafkaTemplate.send("product-updated-topic", updatedProduct.getProductId(), event); // Key by productId
        //log.info("Published ProductUpdatedEvent for Product ID: {} to Kafka topic 'product-updated-topic'", updatedProduct.getProductId());

        return updatedProduct;
    }

    /**
     * Deletes a Product from the PostgreSQL database and then publishes a Kafka event.
     *
     * @param productId The ID of the Product to delete.
     * @throws ResourceNotFoundException if the Product is not found.
     */
    @Transactional
    public void deleteProduct(String productId) {
        //log.info("Deleting Product with ID: {}", productId);

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }

        productRepository.deleteById(productId);
        //log.info("Product deleted successfully from PostgreSQL with ID: {}", productId);

        // Publish Kafka event for deletion
        ProductDeletedEvent event = new ProductDeletedEvent(productId);
        kafkaTemplate.send("product-deleted-topic", productId, event); // Key by productId
        //log.info("Published ProductDeletedEvent for Product ID: {} to Kafka topic 'product-deleted-topic'", productId);
    }

    // You can add a patch method if needed, which would involve selectively updating fields.
    // For simplicity, updateProduct handles full replacement for now.
}