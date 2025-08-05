package com.bhagwat.scm.inventoryService.repository;

import com.bhagwat.scm.inventoryService.entity.InventoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDocumentRepository extends MongoRepository<InventoryDocument, Long> {
}
