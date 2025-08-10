package com.bhagwat.scm.inventoryService.controller;
import com.bhagwat.scm.inventoryService.dto.PackConfigurationDto;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.service.PackConfigurationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing PackConfiguration entities.
 * Handles creation, retrieval, update, and deletion of PackConfigurations.
 */
@RestController
@RequestMapping("/api/v1/pack-configurations")
@Slf4j
@RequiredArgsConstructor
public class PackConfigurationController {

    private final PackConfigurationService packConfigurationService;

    /**
     * Creates a new PackConfiguration.
     *
     * @param packConfigurationDto The DTO containing the details for the new PackConfiguration.
     * @return A ResponseEntity with the created PackConfiguration and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<PackConfiguration> createPackConfiguration(@Valid @RequestBody PackConfigurationDto packConfigurationDto) {
        log.info("Received request to create PackConfiguration: {}", packConfigurationDto.getPackConfigName());
        PackConfiguration createdPackConfig = packConfigurationService.createPackConfiguration(packConfigurationDto);
        return new ResponseEntity<>(createdPackConfig, HttpStatus.CREATED);
    }

    /**
     * Retrieves a PackConfiguration by its ID.
     *
     * @param id The ID of the PackConfiguration to retrieve.
     * @return A ResponseEntity with the found PackConfiguration and HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<PackConfiguration> getPackConfigurationById(@PathVariable Long id) {
        log.info("Received request to get PackConfiguration by ID: {}", id);
        PackConfiguration packConfig = packConfigurationService.getPackConfigurationById(id);
        return ResponseEntity.ok(packConfig);
    }

    /**
     * Retrieves all PackConfigurations.
     *
     * @return A ResponseEntity with a list of all PackConfigurations and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<PackConfiguration>> getAllPackConfigurations() {
        log.info("Received request to get all PackConfigurations");
        List<PackConfiguration> packConfigs = packConfigurationService.getAllPackConfigurations();
        return ResponseEntity.ok(packConfigs);
    }

    /**
     * Updates an existing PackConfiguration.
     *
     * @param id The ID of the PackConfiguration to update.
     * @param packConfigurationDto The DTO containing the updated details.
     * @return A ResponseEntity with the updated PackConfiguration and HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<PackConfiguration> updatePackConfiguration(@PathVariable Long id, @Valid @RequestBody PackConfigurationDto packConfigurationDto) {
        log.info("Received request to update PackConfiguration with ID: {}", id);
        PackConfiguration updatedPackConfig = packConfigurationService.updatePackConfiguration(id, packConfigurationDto);
        return ResponseEntity.ok(updatedPackConfig);
    }

    /**
     * Deletes a PackConfiguration by its ID.
     *
     * @param id The ID of the PackConfiguration to delete.
     * @return A ResponseEntity with no content and HTTP status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackConfiguration(@PathVariable Long id) {
        log.info("Received request to delete PackConfiguration with ID: {}", id);
        packConfigurationService.deletePackConfiguration(id);
        return ResponseEntity.noContent().build();
    }
}
