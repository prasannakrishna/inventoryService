package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.event.InventoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishEvent(InventoryEvent event) {
        kafkaTemplate.send("inventory-events", event);
    }
}
