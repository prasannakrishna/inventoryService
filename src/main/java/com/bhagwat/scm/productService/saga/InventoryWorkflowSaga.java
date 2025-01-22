package com.bhagwat.scm.productService.saga;

import com.bhagwat.scm.productService.dto.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class InventoryWorkflowSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "inventoryId")
    public void handle(InventoryCreatedEvent event) {
        commandGateway.send(new PublishInventoryCommand(event.inventoryId, event.sellerId));
    }

    @SagaEventHandler(associationProperty = "inventoryId")
    public void handle(InventoryPublishedEvent event) {
        commandGateway.send(new TriggerFinanceWorkflowCommand(event.inventoryId, event.sellerId));
    }

    @SagaEventHandler(associationProperty = "inventoryId")
    public void handle(FinanceWorkflowTriggeredEvent event) {
        commandGateway.send(new CreateShipmentCommand(event.inventoryId, event.sellerId, "destination"));
        commandGateway.send(new NotifyCommunityCommand(event.sellerId, "Fresh stock is available!"));
    }

    @SagaEventHandler(associationProperty = "shipmentId")
    public void handle(CarrierConfirmedEvent event) {
        System.out.println("Carrier confirmed for shipment: " + event.shipmentId);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "shipmentId")
    public void handle(ShipmentCreatedEvent event) {
        // End saga after shipment creation
    }
}
