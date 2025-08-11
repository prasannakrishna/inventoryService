package com.bhagwat.scm.inventoryService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_variants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private double communityPrice;
    private double retailPrice;

}
