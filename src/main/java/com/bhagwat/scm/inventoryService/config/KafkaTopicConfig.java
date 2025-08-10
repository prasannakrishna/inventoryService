package com.bhagwat.scm.inventoryService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RefreshScope
public class KafkaTopicConfig {

    // --- Inventory Topic Properties ---
    @Value("${app.kafka.topics.inventory-events.name}")
    private String inventoryTopicName;
    @Value("${app.kafka.topics.inventory-events.partitions}")
    private int inventoryTopicPartitions;
    @Value("${app.kafka.topics.inventory-events.replicas}")
    private short inventoryTopicReplicas;
    @Value("${app.kafka.topics.inventory-events.retention-ms}")
    private String inventoryTopicRetentionMs;
    @Value("${app.kafka.topics.inventory-events.cleanup-policy}")
    private String inventoryTopicCleanupPolicy;


    @Bean
    @RefreshScope
    public NewTopic inventoryEventsTopic() {
        Map<String, String> configs = new HashMap<>();
        configs.put("retention.ms", inventoryTopicRetentionMs);
        configs.put("cleanup.policy", inventoryTopicCleanupPolicy);
        // Add more custom topic configurations here if needed, e.g., "max.message.bytes"

        return TopicBuilder.name(inventoryTopicName)
                .partitions(inventoryTopicPartitions)
                .replicas(inventoryTopicReplicas)
                .configs(configs)
                .build();
    }
}
