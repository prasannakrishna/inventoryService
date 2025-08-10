package com.bhagwat.scm.inventoryService.entity;

import com.bhagwat.scm.inventoryService.constant.CalendarUnit;
import com.bhagwat.scm.inventoryService.constant.CountFrequency;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "product_id", unique = true, nullable = false, length = 50)
    private String productId;

    @Column(name = "seller_id", nullable = false, length = 50)
    private String sellerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skuId", referencedColumnName = "skuId", nullable = false)
    private SKU sku; // Relationship to SKU entity

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @JdbcTypeCode(SqlTypes.JSON) // Maps Map to JSONB in PostgreSQL
    @Column(name = "hash_keys", columnDefinition = "jsonb")
    private Map<String, String> hashKeys = new HashMap<>();

    private boolean isSeasonal;

    private double price;
    private double mRP_Price;

    private boolean is_in_stock;

    private double communityPrice;
    private double retailPrice;

    // it will be used when seller directly sells goods to community without mediator
    @Enumerated(EnumType.STRING)
    private Set<CalendarUnit> allowed_subscription_type;

    private TrackingLevel shipping_tracking_level;

    @Enumerated(EnumType.STRING)
    private CountFrequency frequency;

    private TrackingLevel store_tracking_level;

    private boolean captureExpiryDuringCreateInventory;
}