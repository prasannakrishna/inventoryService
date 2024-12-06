package com.bhagwat.scm.productService.aggregate;
import com.bhagwat.scm.productService.dto.AllocateInventoryCommand;
import com.bhagwat.scm.productService.dto.CreateInventoryCommand;
import com.bhagwat.scm.productService.dto.InventoryAllocatedEvent;
import com.bhagwat.scm.productService.dto.InventoryCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.apache.tomcat.jni.SSLConf.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class InventoryAggregate {

    @AggregateIdentifier
    private String inventoryId;
    private Integer quantity;
    private Integer allocatedQuantity;

    public InventoryAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public InventoryAggregate(CreateInventoryCommand command) {
        apply(new InventoryCreatedEvent(
                command.getInventoryId(),
                command.getSkuId(),
                command.getSellerId(),
                command.getQuantity(),
                command.getStoreId()
        ));
    }

    @EventSourcingHandler
    public void on(InventoryCreatedEvent event) {
        this.inventoryId = event.getInventoryId();
        this.quantity = event.getQuantity();
        this.allocatedQuantity = 0;
    }

    @CommandHandler
    public void handle(AllocateInventoryCommand command) {
        if (quantity < command.getOrderQuantity()) {
            throw new IllegalStateException("Insufficient inventory!");
        }
        apply(new InventoryAllocatedEvent(
                command.getInventoryId(),
                command.getOrderId(),
                command.getOrderQuantity()
        ));
    }

    @EventSourcingHandler
    public void on(InventoryAllocatedEvent event) {
        this.quantity -= event.getOrderQuantity();
        this.allocatedQuantity += event.getOrderQuantity();
    }
}

