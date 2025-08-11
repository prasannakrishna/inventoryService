package com.bhagwat.scm.inventoryService.entity;

import com.bhagwat.scm.inventoryService.constant.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder; // Added Builder for convenience
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant; // For timestamps (UTC, preferred for backend)
import java.time.LocalDate; // For dates without time

@Entity
@Table(name = "inventory") // Good practice to explicitly name your table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder // Added Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    // --- Core Inventory Attributes ---
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading is good for performance
    @JoinColumn(name = "skuId", nullable = false) // An inventory item MUST have an SKU
    private SKU sku; // Represents the specific variant of a product

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id") // Represents the generic product (e.g., iPhone 15 Pro)
    private Product product; // Assuming Product is a separate entity

    @Column(name = "seller_id") // The seller who owns this inventory (could be a Party role)
    private Long sellerId; // Assuming Party is a separate entity for individuals/companies

    @Column(name = "store_id") // If this inventory is specifically for a retail store or distribution center
    private Long storeId; // Assuming Store is a separate entity

    @Column(name = "owner_id") // The ultimate owner of the inventory (e.g., brand, vendor, 3PL client)
    private Long ownerId; // Often distinct from the 'seller' or 'client' (e.g. 3PL might manage for an owner)

    @Column(name = "client_id") // The client for whom this inventory is being managed (esp. for 3PL)
    private Long clientId; // Another Party role

    @Column(nullable = false)
    private String locationId; // The precise physical location within the warehouse (e.g., Aisle-Rack-Level-Bin)

    @Column(nullable = false)
    private Integer quantity; // Current quantity at this location/with these attributes

    @Column(nullable = false)
    private Integer allocatedQuantity; // Quantity reserved for outgoing orders

    private Double pricePerUnit; // Optional, might be managed in ERP

    @Enumerated(EnumType.STRING)
    private Currency currency; // Currency of pricePerUnit

    @Enumerated(EnumType.STRING)
    private UnitTrackingLevel unitTrackingLevel; // How the quantity is tracked (e.g., EACH, CASE)

    // --- Tracking & Traceability Attributes ---
    private String containerId; // Identifier for the physical container (e.g., Pallet ID, Tote ID)
    private String shipmentId; // ID of the inbound shipment this inventory arrived with
    private String carrierId; // ID of the carrier that delivered this shipment
    private String siteId; // Identifier for the specific physical site/warehouse if multiple are managed
    private String orderId; // If this inventory is specifically tied to an incoming purchase order ID or outgoing sales order ID
    private String tagId; // Generic ID for arbitrary tagging/grouping

    @Column(unique = true) // Batch/Lot/Serial numbers often need to be unique or unique per product
    private String lotNumber; // A unique identifier for a batch of products from a specific production run

    @Column(unique = true)
    private String batchNumber; // Often synonymous with lotNumber, or a specific internal batch ID

    @Column(unique = true)
    private String serialNumber; // Unique identifier for an individual item (e.g., for electronics)

    private String receiptId; // ID of the specific warehouse receipt document (proof of incoming goods)

    private String origin; // Where the inventory originally came from (e.g., country, manufacturing plant ID)
    private String inventoryCode; // An internal tracking code for this specific inventory record/batch

    @Enumerated(EnumType.STRING)
    private InventoryType inventoryType; // e.g., FINISHED_GOODS, RAW_MATERIAL, WORK_IN_PROGRESS

    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus; // Current state of the inventory (e.g., AVAILABLE, ON_HOLD, DAMAGED)

    private LocalDate expiryDate; // For perishable goods

    // --- Auditing Timestamps ---
    @CreationTimestamp // Automatically sets the creation timestamp on entity persistence
    @Column(name = "created_date", nullable = false, updatable = false)
    private Instant createdDate;

    @UpdateTimestamp // Automatically updates the timestamp on entity modification
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "last_stock_check_date")
    private Instant lastStockCheckDate;

    @Column(name = "last_cycle_count_date")
    private Instant lastCycleCountDate;

    // --- Enums ---
    public enum Currency {
        INR, USD, EUR, GBP, JPY
    }

    public enum UnitTrackingLevel {
        EACH, BOX, CASE, PALLET
    }

    public enum InventoryType {
        FINISHED_GOODS, RAW_MATERIAL, WORK_IN_PROGRESS, SUPPLIES, PACKAGING, ASSET
        // Add more types relevant to your business
    }

    private boolean reserved_for_community_orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    private ProductVariant productVariant;
}
