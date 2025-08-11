package com.bhagwat.scm.inventoryService.converter;
import com.bhagwat.scm.inventoryService.constant.TrackingLevel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TrackingLevelConverter implements AttributeConverter<TrackingLevel, Short> {

    @Override
    public Short convertToDatabaseColumn(TrackingLevel level) {
        return (level != null) ? level.getCode() : null;
    }

    @Override
    public TrackingLevel convertToEntityAttribute(Short dbData) {
        return (dbData != null) ? TrackingLevel.fromCode(dbData) : null;
    }
}