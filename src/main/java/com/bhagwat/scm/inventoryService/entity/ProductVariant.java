package com.bhagwat.scm.inventoryService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "product_variants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariant {
    @Id
    @Column(name = "variant_id", length = 50)
    private String variantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity")
    private double quantity; // e.g., 250, 500, 1

    @Column(name = "uom", length = 20)
    private String uom; // e.g., "g", "kg", "ml"

    @Column(name = "flavor", length = 50)
    private String flavor; // e.g., "mango", "lemon"

    @Column(name = "price")
    private double price;

    @Column(name = "sku_id", unique = true, nullable = false)
    private String skuId; // unique identifier for inventory tracking
}
