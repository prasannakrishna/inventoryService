package com.bhagwat.scm.inventoryService.service;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.entity.Product;
import com.bhagwat.scm.inventoryService.entity.ProductDocument;
import com.bhagwat.scm.inventoryService.entity.SKU;
import com.bhagwat.scm.inventoryService.event.ProductCreatedEvent;
import com.bhagwat.scm.inventoryService.event.ProductUpdatedEvent;
import com.bhagwat.scm.inventoryService.event.ProductDeletedEvent;
import com.bhagwat.scm.inventoryService.repository.ProductDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventConsumer {

    private final ProductDocumentRepository productDocumentRepository;

    /**
     * Handles a new product creation event and inserts it into MongoDB.
     */
    @KafkaListener(topics = "product-created-topic", groupId = "product-mongo-updater-group")
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        Product product = event.getProduct();
        PackConfiguration packConfiguration = event.getPackConfiguration();
        SKU sku = event.getSku();
        ProductDocument productDocument = createProductDocument(product, packConfiguration, sku);
        productDocumentRepository.save(productDocument);
    }

    /**
     * Handles a product update event and updates the MongoDB record.
     * save() acts as upsert in Mongo.
     */
    @KafkaListener(topics = "product-updated-topic", groupId = "product-mongo-updater-group")
    public void handleProductUpdatedEvent(ProductUpdatedEvent event) {
        Product product = event.getProduct();
        PackConfiguration packConfiguration = event.getPackConfiguration();
        SKU sku = event.getSku();
        ProductDocument productDocument = createProductDocument(product, packConfiguration, sku);
        productDocumentRepository.save(productDocument);
    }

    /**
     * Handles a product deletion event and removes the document from MongoDB.
     */
    @KafkaListener(topics = "product-deleted-topic", groupId = "product-mongo-updater-group")
    public void handleProductDeletedEvent(ProductDeletedEvent event) {
        productDocumentRepository.deleteById(event.getProductId());
    }

    /**
     * Helper method to map a Product entity to a ProductDocument.
     */
    private ProductDocument createProductDocument(Product product, PackConfiguration packConfiguration, SKU sku) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setProductId(product.getProductId());
        productDocument.setProductName(product.getProductName());
        productDocument.setFrequency(product.getFrequency());
        productDocument.setHashKeys(product.getHashKeys());
        productDocument.set_in_stock(product.is_in_stock());
        productDocument.setDescription(product.getDescription());
        productDocument.setSku(sku);
        //productDocument.setPackingConfiguration(packConfiguration);
        productDocument.setBrand(product.getBrand());
        productDocument.setMRP_Price(product.getMRP_Price());
        productDocument.setPrice(product.getPrice());
        productDocument.setShipping_tracking_level(product.getShipping_tracking_level());
        productDocument.setSeasonal(product.isSeasonal());
        productDocument.setStore_tracking_level(product.getStore_tracking_level());
        productDocument.setSellerId(product.getSellerId());
        return productDocument;
    }
}