package com.bhagwat.scm.productService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackingConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packConfigId;

    private String packConfigName;

    private Double tagVolume;
    private Double eachVolume;

    @Enumerated(EnumType.STRING)
    private TrackingLevel trackingLevel1;

    @Enumerated(EnumType.STRING)
    private TrackingLevel trackingLevel2;

    @Enumerated(EnumType.STRING)
    private TrackingLevel trackingLevel3;

    private Integer trackingLevel1To2Ratio;
    private Integer trackingLevel2To3Ratio;

    public enum TrackingLevel {
        EACH, CASE, BOX, PALLET
    }

    // Getters and Setters
    public Long getPackConfigId() {
        return packConfigId;
    }

    public void setPackConfigId(Long packConfigId) {
        this.packConfigId = packConfigId;
    }

    // Add getters and setters for other fields
}

