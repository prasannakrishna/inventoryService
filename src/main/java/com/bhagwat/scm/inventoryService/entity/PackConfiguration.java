package com.bhagwat.scm.inventoryService.entity;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pack_configuration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pack_configuration_id")
    private Long id;

    @Column(name = "pcName")
    private String packConfigName;

    private Double tagVolume;

    private Double eachVolume;

    @Enumerated(EnumType.STRING)
    @Column(name = "tracking_level_1", nullable = false)
    private TrackingLevel trackingLevel1;

    @Enumerated(EnumType.STRING)
    @Column(name = "tracking_level_2")
    private TrackingLevel trackingLevel2;

    @Column(name = "tracking_ratio_1_to_2", nullable = false)
    private Integer trackingRatio1To2;

    @Enumerated(EnumType.STRING)
    @Column(name = "tracking_level_3")
    private TrackingLevel trackingLevel3;

    @Column(name = "tracking_ratio_2_to_3")
    private Integer trackingRatio2To3;

    @Enumerated(EnumType.STRING)
    @Column(name = "tracking_level_4")
    private TrackingLevel trackingLevel4;

    @Column(name = "tracking_ratio_3_to_4")
    private Integer trackingRatio3To4;

    @Enumerated(EnumType.STRING)
    @Column(name = "tracking_level_5")
    private TrackingLevel trackingLevel5;

    @Column(name = "tracking_ratio_4_to_5")
    private Integer trackingRatio4To5;

    @Column(name = "lowest_unit_weight", precision = 10, scale = 2)
    private BigDecimal lowestUnitWeight;

    @Column(name = "tag_volume_weight", precision = 10, scale = 2)
    private BigDecimal tagVolumeWeight;

    @Enumerated(EnumType.STRING)
    @Column(name = "tagged_at_level")
    private TrackingLevel taggedAtLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "handling_unit_level")
    private TrackingLevel handlingUnitLevel;
}

