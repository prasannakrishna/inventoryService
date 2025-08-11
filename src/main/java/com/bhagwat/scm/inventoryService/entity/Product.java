package com.bhagwat.scm.inventoryService.entity;

import com.bhagwat.scm.inventoryService.constant.CalendarUnit;
import com.bhagwat.scm.inventoryService.constant.CountFrequency;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import com.bhagwat.scm.inventoryService.converter.TrackingLevelConverter;
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
    private Set<String> hashKeys = new HashSet<>();

    @Column(name = "is_seasonal")
    private boolean isSeasonal;

    @Column(name = "price")
    private double price;

    @Column(name = "mrp_price")
    private double mRP_Price;

    @Column(name = "is_in_stock")
    private boolean is_in_stock;

    @Column(name = "community_price")
    private double communityPrice;

    @Column(name = "retail_price")
    private double retailPrice;

    // it will be used when seller directly sells goods to community without mediator
    @Enumerated(EnumType.STRING)
    @Column(name = "allowed_subscription_type")
    private Set<CalendarUnit> allowed_subscription_type;

    @Column(name = "shipping_tracking_level")
    @Convert(converter = TrackingLevelConverter.class)
    private TrackingLevel shipping_tracking_level;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency")
    private CountFrequency frequency;

    @Convert(converter = TrackingLevelConverter.class)
    @Column(name = "store_tracking_level")
    private TrackingLevel store_tracking_level;

    @Column(name = "capture_expiry_during_create_inventory")
    private boolean captureExpiryDuringCreateInventory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProductVariant> variants = new HashSet<>();
}