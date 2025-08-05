package com.bhagwat.scm.inventoryService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private String orderId;
    private Long customerId;
    private Long skuId;
    private String orderStatus; // "CREATED", "ALLOCATED", etc.
    private LocalDateTime orderCreatedDate;
    private LocalDateTime orderLastUpdatedDate;
    private LocalDateTime orderShipByDate;
    private LocalDateTime orderDeliverByDate;
    private Long orderLineId;
    private Long consignmentId;
    private Long sellerId;
    private Long carrierId;
    private Address sourceAddress;
    private Address targetAddress;
    private Integer orderQuantity;

    // Constructor, Getters and Setters
}

