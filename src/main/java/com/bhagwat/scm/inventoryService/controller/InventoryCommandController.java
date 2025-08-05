package com.bhagwat.scm.inventoryService.controller;

import com.bhagwat.scm.inventoryService.command.InventoryCommandDTO;
import com.bhagwat.scm.inventoryService.dto.InventoryDto;
import com.bhagwat.scm.inventoryService.entity.Inventory;
import com.bhagwat.scm.inventoryService.service.InventoryCommandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
public class InventoryCommandController {
    private final InventoryCommandService service;

    @PostMapping
    public ResponseEntity<Inventory> create(@RequestBody InventoryDto dto) {
        return ResponseEntity.ok(service.createInventory(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody InventoryDto dto) {
        service.updateInventory(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Void> patch(@PathVariable Long id, @RequestBody JsonMergePatch patch)
            throws JsonProcessingException {
        //service.patchInventory(id, patch);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}