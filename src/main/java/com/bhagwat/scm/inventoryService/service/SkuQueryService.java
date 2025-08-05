package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.dto.SkuDocumentDto;
import com.bhagwat.scm.inventoryService.entity.SKUDocument;
import com.bhagwat.scm.inventoryService.repository.SkuDocumentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkuQueryService {

    private final SkuDocumentRepository readRepository;

    public SKUDocument getById(String id) {
        return readRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("SKU not found in read model"));
    }

    public List<SKUDocument> getAll() {
        return readRepository.findAll();
    }

    public SkuDocumentDto findById(Long skuId) {
        //log.info("Querying for SKU with ID: {}", skuId);
        SKUDocument skuDocument = readRepository.findById(skuId)
                .orElseThrow(() -> new ResourceNotFoundException("SKU not found with id: " + skuId));

        // You would use a mapper here (e.g., MapStruct) to convert the document to a DTO
        SkuDocumentDto dto = new SkuDocumentDto();
        dto.setSkuId(skuDocument.getSkuId());
        dto.setSkuName(skuDocument.getSkuName());
        dto.setProductGroup(skuDocument.getProductGroup());
        dto.setLength(skuDocument.getLength());
        dto.setWidth(skuDocument.getWidth());
        dto.setHeight(skuDocument.getHeight());
        dto.setWeight(skuDocument.getWeight());
        dto.setIsHazardous(skuDocument.getIsHazardous());
        dto.setTrackingLevel(skuDocument.getTrackingLevel());
        dto.setIsSellerSKU(skuDocument.getIsSellerSKU());
        dto.setPackingConfiguration(skuDocument.getPackingConfiguration());
        dto.setSellerId(skuDocument.getSellerId());
        dto.setUomWeight(skuDocument.getUomWeight());
        dto.setUomVolume(skuDocument.getUomVolume());
        dto.setUomLength(skuDocument.getUomLength());
        dto.setUomDimension(skuDocument.getUomDimension());
        dto.setSkuState(skuDocument.getSkuState());
        dto.setPacking_tracking_level(skuDocument.getPacking_tracking_level());
        dto.setShipping_tracking_level(skuDocument.getShipping_tracking_level());
        dto.setTaggedAtLevel(skuDocument.getTaggedAtLevel());

        return dto;
    }

    /**
     * Finds all SKU documents from the MongoDB query database.
     *
     * @return A list of all SkuDocumentDto objects.
     */
    public List<SkuDocumentDto> findAll() {
        //log.info("Querying for all SKUs");
        List<SKUDocument> skuDocuments = readRepository.findAll();
        // You would use a mapper to convert the list of documents to a list of DTOs
        return skuDocuments.stream().map(skuDocument -> {
            SkuDocumentDto dto = new SkuDocumentDto();
            dto.setSkuId(skuDocument.getSkuId());
            dto.setSkuName(skuDocument.getSkuName());
            dto.setProductGroup(skuDocument.getProductGroup());
            dto.setLength(skuDocument.getLength());
            dto.setWidth(skuDocument.getWidth());
            dto.setHeight(skuDocument.getHeight());
            dto.setWeight(skuDocument.getWeight());
            dto.setIsHazardous(skuDocument.getIsHazardous());
            dto.setTrackingLevel(skuDocument.getTrackingLevel());
            dto.setIsSellerSKU(skuDocument.getIsSellerSKU());
            dto.setPackingConfiguration(skuDocument.getPackingConfiguration());
            dto.setSellerId(skuDocument.getSellerId());
            dto.setUomWeight(skuDocument.getUomWeight());
            dto.setUomVolume(skuDocument.getUomVolume());
            dto.setUomLength(skuDocument.getUomLength());
            dto.setUomDimension(skuDocument.getUomDimension());
            dto.setSkuState(skuDocument.getSkuState());
            dto.setPacking_tracking_level(skuDocument.getPacking_tracking_level());
            dto.setShipping_tracking_level(skuDocument.getShipping_tracking_level());
            dto.setTaggedAtLevel(skuDocument.getTaggedAtLevel());
            return dto;
        }).collect(Collectors.toList());
    }
}
