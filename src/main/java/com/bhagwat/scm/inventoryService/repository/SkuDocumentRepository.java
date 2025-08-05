package com.bhagwat.scm.inventoryService.repository;

import com.bhagwat.scm.inventoryService.entity.SKUDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuDocumentRepository extends MongoRepository<SKUDocument, Long> {
}