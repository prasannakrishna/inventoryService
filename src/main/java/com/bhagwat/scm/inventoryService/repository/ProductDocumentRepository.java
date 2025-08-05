package com.bhagwat.scm.inventoryService.repository;

import com.bhagwat.scm.inventoryService.entity.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDocumentRepository extends MongoRepository<ProductDocument, String> { // ProductId is String
}