package com.bhagwat.scm.inventoryService.controller;

import com.bhagwat.scm.inventoryService.dto.ProductDocumentDto;
import com.bhagwat.scm.inventoryService.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductQueryController {

    private final ProductQueryService productQueryService;


    /**
     * Endpoint to find a single Product by its ID.
     *
     * @param productId The ID of the Product.
     * @return A ResponseEntity with the found ProductDocumentDto.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDocumentDto> findProductById(@PathVariable String productId) {
        //log.info("Received request to find Product by ID: {}", productId);
        ProductDocumentDto product = productQueryService.findById(productId);
        return ResponseEntity.ok(product);
    }

    /**
     * Endpoint to find all Products.
     *
     * @return A ResponseEntity with a list of all ProductDocumentDto objects.
     */
    @GetMapping
    public ResponseEntity<List<ProductDocumentDto>> findAllProducts() {
        //log.info("Received request to find all Products");
        List<ProductDocumentDto> products = productQueryService.findAll();
        return ResponseEntity.ok(products);
    }
}