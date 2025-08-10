package com.bhagwat.scm.inventoryService.repository;

import com.bhagwat.scm.inventoryService.entity.InventoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryDocumentRepository extends MongoRepository<InventoryDocument, Long> {
}
