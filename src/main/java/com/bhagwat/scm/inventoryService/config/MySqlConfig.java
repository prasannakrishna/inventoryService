package com.bhagwat.scm.inventoryService.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.inventory.repository.command")
@EntityScan(basePackages = "com.example.inventory.entity")
public class MySqlConfig {
}
