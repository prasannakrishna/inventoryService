package com.bhagwat.scm.productService.controller;

import com.bhagwat.scm.productService.dto.AllocateInventoryCommand;
import com.bhagwat.scm.productService.dto.AllocateInventoryRequest;
import com.bhagwat.scm.productService.dto.CreateInventoryCommand;
import com.bhagwat.scm.productService.dto.CreateInventoryRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<String> createInventory(@RequestBody CreateInventoryRequest request) {
        String inventoryId = UUID.randomUUID().toString();
        commandGateway.send(new CreateInventoryCommand(
                inventoryId,
                request.getSkuId(),
                request.getSellerId(),
                request.getQuantity(),
                request.getStoreId()
        ));
        return ResponseEntity.ok(inventoryId);
    }


    @PostMapping("/allocate")
    public ResponseEntity<String> allocateInventory(@RequestBody AllocateInventoryRequest request) {
        commandGateway.send(new AllocateInventoryCommand(
                request.getInventoryId(),
                request.getOrderId(),
                request.getOrderQuantity()
        ));
        return ResponseEntity.ok("Inventory Allocated!");
    }
}

