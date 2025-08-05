package com.bhagwat.scm.inventoryService.repository;

import com.bhagwat.scm.inventoryService.entity.SKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends JpaRepository<SKU, Long> {}