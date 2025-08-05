package com.bhagwat.scm.inventoryService.controller;

import com.bhagwat.scm.inventoryService.dto.ProductDTO;
import com.bhagwat.scm.inventoryService.entity.Product;
import com.bhagwat.scm.inventoryService.service.ProductCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductCommandService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDto) {
        //log.info("Received request to create Product: {}", productDto.getProductId());
        Product createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @Valid @RequestBody ProductDTO productDto) {
        //log.info("Received request to update Product ID: {}", productId);
        Product updatedProduct = productService.updateProduct(productId, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Endpoint to delete a Product by its ID.
     *
     * @param productId The ID of the Product to delete.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        //log.info("Received request to delete Product ID: {}", productId);
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // For PATCH, you would typically have a separate DTO with optional fields
    // and a service method that applies partial updates.
    // Example:
    /*
    @PatchMapping("/{productId}")
    public ResponseEntity<Product> patchProduct(@PathVariable String productId, @RequestBody Map<String, Object> updates) {
        log.info("Received request to patch Product ID: {}", productId);
        Product patchedProduct = productService.patchProduct(productId, updates); // You'd implement this in ProductService
        return ResponseEntity.ok(patchedProduct);
    }
    */
}