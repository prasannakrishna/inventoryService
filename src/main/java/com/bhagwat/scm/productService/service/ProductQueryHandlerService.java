package com.bhagwat.scm.productService.service;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class ProductQueryHandlerService {
    private final InventoryViewRepository inventoryViewRepository;
    private final SKUViewRepository skuViewRepository;

    public ProductQueryHandlerService(InventoryViewRepository inventoryViewRepository, SKUViewRepository skuViewRepository) {
        this.inventoryViewRepository = inventoryViewRepository;
        this.skuViewRepository = skuViewRepository;
    }

    @QueryHandler
    public List<InventoryView> handle(GetInventoryQuery query) {
        return inventoryViewRepository.findAll();
    }

    @QueryHandler
    public List<SKUView> handle(GetSKUQuery query) {
        return skuViewRepository.findAll();
    }
}
