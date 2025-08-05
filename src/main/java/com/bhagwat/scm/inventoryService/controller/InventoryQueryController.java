package com.bhagwat.scm.inventoryService.controller;

import com.bhagwat.scm.inventoryService.dto.InventoryDocumentDto;
import com.bhagwat.scm.inventoryService.service.InventoryQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryQueryController {

    private final InventoryQueryService inventoryQueryService;

    public InventoryQueryController(InventoryQueryService inventoryQueryService) {
        this.inventoryQueryService = inventoryQueryService;
    }

    /**
     * Endpoint to find a single Inventory item by its ID.
     *
     * @param inventoryId The ID of the Inventory item.
     * @return A ResponseEntity with the found InventoryDocumentDto.
     */
    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryDocumentDto> findInventoryById(@PathVariable Long inventoryId) {
        //log.info("Received request to find Inventory by ID: {}", inventoryId);
        InventoryDocumentDto inventory = inventoryQueryService.findById(inventoryId);
        return ResponseEntity.ok(inventory);
    }

    /**
     * Endpoint to find all Inventory items.
     *
     * @return A ResponseEntity with a list of all InventoryDocumentDto objects.
     */
    @GetMapping
    public ResponseEntity<List<InventoryDocumentDto>> findAllInventory() {
        //log.info("Received request to find all Inventory items");
        List<InventoryDocumentDto> inventoryItems = inventoryQueryService.findAll();
        return ResponseEntity.ok(inventoryItems);
    }
}