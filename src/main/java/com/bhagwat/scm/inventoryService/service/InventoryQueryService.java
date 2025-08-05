package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.dto.InventoryDocumentDto;
import com.bhagwat.scm.inventoryService.entity.InventoryDocument;
import com.bhagwat.scm.inventoryService.repository.InventoryDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryQueryService {
    private final InventoryDocumentRepository inventoryDocumentRepository;


    /**
     * Finds a single Inventory document by its ID from the MongoDB query database.
     *
     * @param inventoryId The ID of the Inventory item to find.
     * @return The found InventoryDocumentDto.
     * @throws ResourceNotFoundException if the Inventory item is not found.
     */
    public InventoryDocumentDto findById(Long inventoryId) {
        //log.info("Querying for Inventory with ID: {}", inventoryId);
        InventoryDocument inventoryDocument = inventoryDocumentRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id: " + inventoryId));

        // Map the document to a DTO (using MapStruct would be ideal here)
        return mapToInventoryDocumentDto(inventoryDocument);
    }

    /**
     * Finds all Inventory documents from the MongoDB query database.
     *
     * @return A list of all InventoryDocumentDto objects.
     */
    public List<InventoryDocumentDto> findAll() {
        //log.info("Querying for all Inventory items");
        List<InventoryDocument> inventoryDocuments = inventoryDocumentRepository.findAll();
        // Map the list of documents to a list of DTOs
        return inventoryDocuments.stream()
                .map(this::mapToInventoryDocumentDto)
                .collect(Collectors.toList());
    }

    /**
     * Helper method to map an InventoryDocument to an InventoryDocumentDto.
     * @param inventoryDocument The InventoryDocument to map.
     * @return The mapped InventoryDocumentDto.
     */
    private InventoryDocumentDto mapToInventoryDocumentDto(InventoryDocument inventoryDocument) {
        InventoryDocumentDto dto = new InventoryDocumentDto();
        dto.setInventoryId(inventoryDocument.getInventoryId());
        dto.setSkuId(inventoryDocument.getSkuId());
        dto.setProductId(inventoryDocument.getProductId());
        dto.setSellerId(inventoryDocument.getSellerId());
        dto.setStoreId(inventoryDocument.getStoreId());
        dto.setOwnerId(inventoryDocument.getOwnerId());
        dto.setClientId(inventoryDocument.getClientId());
        dto.setLocationId(inventoryDocument.getLocationId());
        dto.setQuantity(inventoryDocument.getQuantity());
        dto.setAllocatedQuantity(inventoryDocument.getAllocatedQuantity());
        dto.setPricePerUnit(inventoryDocument.getPricePerUnit());
        dto.setCurrency(inventoryDocument.getCurrency());
        dto.setUnitTrackingLevel(inventoryDocument.getUnitTrackingLevel());
        dto.setContainerId(inventoryDocument.getContainerId());
        dto.setShipmentId(inventoryDocument.getShipmentId());
        dto.setCarrierId(inventoryDocument.getCarrierId());
        dto.setSiteId(inventoryDocument.getSiteId());
        dto.setOrderId(inventoryDocument.getOrderId());
        dto.setTagId(inventoryDocument.getTagId());
        dto.setLotNumber(inventoryDocument.getLotNumber());
        dto.setBatchNumber(inventoryDocument.getBatchNumber());
        dto.setSerialNumber(inventoryDocument.getSerialNumber());
        dto.setReceiptId(inventoryDocument.getReceiptId());
        dto.setOrigin(inventoryDocument.getOrigin());
        dto.setInventoryCode(inventoryDocument.getInventoryCode());
        dto.setInventoryType(inventoryDocument.getInventoryType());
        dto.setInventoryStatus(inventoryDocument.getInventoryStatus());
        dto.setExpiryDate(inventoryDocument.getExpiryDate());
        dto.setCreatedDate(inventoryDocument.getCreatedDate());
        dto.setLastModifiedDate(inventoryDocument.getLastModifiedDate());
        dto.setLastStockCheckDate(inventoryDocument.getLastStockCheckDate());
        dto.setLastCycleCountDate(inventoryDocument.getLastCycleCountDate());

        // Denormalized Product Details
        dto.setProductName(inventoryDocument.getProductName());
        dto.setProductBrand(inventoryDocument.getProductBrand());
        dto.setProductDescription(inventoryDocument.getProductDescription());
        dto.setProductHashKeys(inventoryDocument.getProductHashKeys());
        dto.setProductIsSeasonal(inventoryDocument.isProductIsSeasonal());
        dto.setProductPrice(inventoryDocument.getProductPrice());
        dto.setProductMRP_Price(inventoryDocument.getProductMRP_Price());
        dto.setProductIs_in_stock(inventoryDocument.isProductIs_in_stock());
        dto.setProductShippingTrackingLevel(inventoryDocument.getProductShippingTrackingLevel());
        dto.setProductFrequency(inventoryDocument.getProductFrequency());
        dto.setProductStoreTrackingLevel(inventoryDocument.getProductStoreTrackingLevel());
        dto.setProductCaptureExpiryDuringCreateInventory(inventoryDocument.isProductCaptureExpiryDuringCreateInventory());

        // Denormalized SKU Details
        dto.setSkuName(inventoryDocument.getSkuName());
        dto.setSkuProductGroup(inventoryDocument.getSkuProductGroup());
        dto.setSkuLength(inventoryDocument.getSkuLength());
        dto.setSkuWidth(inventoryDocument.getSkuWidth());
        dto.setSkuHeight(inventoryDocument.getSkuHeight());
        dto.setSkuWeight(inventoryDocument.getSkuWeight());
        dto.setSkuIsHazardous(inventoryDocument.getSkuIsHazardous());
        dto.setSkuTrackingLevel(inventoryDocument.getSkuTrackingLevel());
        dto.setSkuIsSellerSKU(inventoryDocument.getSkuIsSellerSKU());
        dto.setSkuUomWeight(inventoryDocument.getSkuUomWeight());
        dto.setSkuUomVolume(inventoryDocument.getSkuUomVolume());
        dto.setSkuUomLength(inventoryDocument.getSkuUomLength());
        dto.setSkuUomDimension(inventoryDocument.getSkuUomDimension());
        dto.setSkuState(inventoryDocument.getSkuState());
        dto.setSkuPackingTrackingLevel(inventoryDocument.getSkuPackingTrackingLevel());
        dto.setSkuShippingTrackingLevel(inventoryDocument.getSkuShippingTrackingLevel());

        // Denormalized PackConfiguration Details
        dto.setPackingConfiguration(inventoryDocument.getPackingConfiguration());

        return dto;
    }
}
