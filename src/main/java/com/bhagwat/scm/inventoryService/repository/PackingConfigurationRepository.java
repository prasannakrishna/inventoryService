package com.bhagwat.scm.inventoryService.repository;

import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  PackingConfigurationRepository extends JpaRepository<PackConfiguration, Long> {
    Optional<PackConfiguration> findById(Long id);
}