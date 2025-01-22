package com.bhagwat.scm.productService.saga;

import com.bhagwat.scm.productService.dto.AllocateInventoryCommand;
import com.bhagwat.scm.productService.dto.InventoryAllocatedEvent;
import com.bhagwat.scm.productService.dto.OrderCreatedEvent;
import com.bhagwat.scm.productService.entity.Inventory;
import com.bhagwat.scm.productService.repository.InventoryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Saga
public class InventoryAllocationSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private InventoryRepository inventoryRepository;

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        Optional<Inventory> inventor = findInventoryId(String.valueOf(event.getSkuId()), event.getSellerId());
        commandGateway.send(new AllocateInventoryCommand(
                inventor.get().getInventoryId()+"",
                event.getOrderId(),
                event.getOrderQuantity()
        ));
    }

    @SagaEventHandler(associationProperty = "inventoryId")
    public void handle(InventoryAllocatedEvent event) {
        SagaLifecycle.end();
    }

    private Optional<Inventory> findInventoryId(String skuId, Long sellerId) {
        // Query inventory repository
        return inventoryRepository.findById(sellerId);
    }
}

