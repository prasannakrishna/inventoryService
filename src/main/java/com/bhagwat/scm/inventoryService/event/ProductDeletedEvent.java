package com.bhagwat.scm.inventoryService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDeletedEvent implements Serializable {
    private String productId;
}