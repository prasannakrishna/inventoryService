package com.bhagwat.scm.productService.aggregate;

import com.bhagwat.scm.productService.dto.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class WorkflowAggregate {
    @AggregateIdentifier
    private String inventoryId;

    public WorkflowAggregate() {}

    @CommandHandler
    public WorkflowAggregate(PublishInventoryCommand command) {
        apply(new InventoryPublishedEvent(command.inventoryId, command.sellerId));
    }

    @EventSourcingHandler
    public void on(InventoryPublishedEvent event) {
        this.inventoryId = event.inventoryId;
    }

    @CommandHandler
    public void handle(CreateShipmentCommand command) {
        String shipmentId = "SHIP_" + command.inventoryId;
        apply(new ShipmentCreatedEvent(shipmentId, command.inventoryId, command.sellerId));
        apply(new CarrierBookingRequestEvent(shipmentId, "warehouse-1", command.destination, 100));
    }

    @EventSourcingHandler
    public void on(ShipmentCreatedEvent event) {
        // Aggregate state update
    }
}
