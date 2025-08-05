package com.bhagwat.scm.inventoryService.event;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.entity.SKU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Kafka event payload to be published after a new SKU is created.
 * It contains the full SKU entity and its corresponding PackConfiguration
 * so the consumer can build the denormalized document without
 * making another database call.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuCreatedEvent implements Serializable {

    private SKU sku;
    private PackConfiguration packConfiguration;
}
