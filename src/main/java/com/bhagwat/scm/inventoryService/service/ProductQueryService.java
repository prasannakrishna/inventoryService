package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.dto.ProductDocumentDto;
import com.bhagwat.scm.inventoryService.entity.ProductDocument;
import com.bhagwat.scm.inventoryService.repository.ProductDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductQueryService {

    private final ProductDocumentRepository productDocumentRepository;

    public ProductQueryService(ProductDocumentRepository productDocumentRepository) {
        this.productDocumentRepository = productDocumentRepository;
    }

    /**
     * Finds a single Product document by its ID from the MongoDB query database.
     *
     * @param productId The ID of the Product to find.
     * @return The found ProductDocumentDto.
     * @throws ResourceNotFoundException if the Product is not found.
     */
    public ProductDocumentDto findById(String productId) {
        //log.info("Querying for Product with ID: {}", productId);
        ProductDocument productDocument = productDocumentRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        // Map the document to a DTO (using MapStruct would be ideal here)
        return mapToProductDocumentDto(productDocument);
    }

    /**
     * Finds all Product documents from the MongoDB query database.
     *
     * @return A list of all ProductDocumentDto objects.
     */
    public List<ProductDocumentDto> findAll() {
        //log.info("Querying for all Products");
        List<ProductDocument> productDocuments = productDocumentRepository.findAll();
        // Map the list of documents to a list of DTOs
        return productDocuments.stream()
                .map(this::mapToProductDocumentDto)
                .collect(Collectors.toList());
    }

    /**
     * Helper method to map a ProductDocument to a ProductDocumentDto.
     * @param productDocument The ProductDocument to map.
     * @return The mapped ProductDocumentDto.
     */
    private ProductDocumentDto mapToProductDocumentDto(ProductDocument productDocument) {
        ProductDocumentDto dto = new ProductDocumentDto();
        dto.setProductId(productDocument.getProductId());
        dto.setSellerId(productDocument.getSellerId());
       /* dto.setSkuId(productDocument.getSkuId());
        dto.setSkuName(productDocument.getSkuName());
        dto.setSkuProductGroup(productDocument.getSkuProductGroup());
        dto.setSkuLength(productDocument.getSkuLength());
        dto.setSkuWidth(productDocument.getSkuWidth());
        dto.setSkuHeight(productDocument.getSkuHeight());
        dto.setSkuWeight(productDocument.getSkuWeight());
        dto.setSkuIsHazardous(productDocument.getSkuIsHazardous());
        dto.setSkuTrackingLevel(productDocument.getSkuTrackingLevel());
        dto.setSkuIsSellerSKU(productDocument.getSkuIsSellerSKU());
        dto.setSkuSellerPartyId(productDocument.getSkuSellerPartyId());
        dto.setSkuUomWeight(productDocument.getSkuUomWeight());
        dto.setSkuUomVolume(productDocument.getSkuUomVolume());
        dto.setSkuUomLength(productDocument.getSkuUomLength());
        dto.setSkuUomDimension(productDocument.getSkuUomDimension());
        dto.setSkuState(productDocument.getSkuState());
        dto.setSkuPackingTrackingLevel(productDocument.getSkuPackingTrackingLevel());
        dto.setSkuShippingTrackingLevel(productDocument.getSkuShippingTrackingLevel());*/
        dto.setPackingConfiguration(productDocument.getPackingConfiguration());
        dto.setProductName(productDocument.getProductName());
        dto.setBrand(productDocument.getBrand());
        dto.setDescription(productDocument.getDescription());
        dto.setHashKeys(productDocument.getHashKeys());
        dto.setSeasonal(productDocument.isSeasonal());
        dto.setPrice(productDocument.getPrice());
        dto.setMRP_Price(productDocument.getMRP_Price());
        dto.set_in_stock(productDocument.is_in_stock());
        dto.setShipping_tracking_level(productDocument.getShipping_tracking_level());
        dto.setFrequency(productDocument.getFrequency());
        dto.setStore_tracking_level(productDocument.getStore_tracking_level());
        dto.setCaptureExpiryDuringCreateInventory(productDocument.isCaptureExpiryDuringCreateInventory());
        return dto;
    }
}
