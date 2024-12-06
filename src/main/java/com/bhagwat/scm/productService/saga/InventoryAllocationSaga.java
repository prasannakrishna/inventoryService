package com.bhagwat.scm.productService.saga;

import com.bhagwat.scm.productService.dto.AllocateInventoryCommand;
import com.bhagwat.scm.productService.dto.InventoryAllocatedEvent;
import com.bhagwat.scm.productService.dto.OrderCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class InventoryAllocationSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        String inventoryId = findInventoryId(String.valueOf(event.getSkuId()), event.getSellerId());
        commandGateway.send(new AllocateInventoryCommand(
                inventoryId,
                event.getOrderId(),
                event.getOrderQuantity()
        ));
    }

    @SagaEventHandler(associationProperty = "inventoryId")
    public void handle(InventoryAllocatedEvent event) {
        SagaLifecycle.end();
    }

    private String findInventoryId(String skuId, Long sellerId) {
        // Query inventory repository
        return "";
    }
}

