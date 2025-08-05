package com.bhagwat.scm.inventoryService.controller;

import com.bhagwat.scm.inventoryService.dto.SkuDTO;
import com.bhagwat.scm.inventoryService.service.SkuCommandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;

@RestController
@RequestMapping("/api/v1/skus")
@RequiredArgsConstructor
public class SkuCommandController {

    private final SkuCommandService service;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody SkuDTO dto) {
        return ResponseEntity.ok(service.createSku(dto).getSkuId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody SkuDTO dto) {
        //service.updateSku(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Void> patch(@PathVariable Long id, @RequestBody JsonMergePatch patch)
            throws JsonProcessingException {
        //service.patchSku(id, patch);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        //service.deleteSku(id);
        return ResponseEntity.noContent().build();
    }
}