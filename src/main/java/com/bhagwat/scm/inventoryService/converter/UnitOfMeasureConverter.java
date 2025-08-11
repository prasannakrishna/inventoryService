package com.bhagwat.scm.inventoryService.converter;
import com.bhagwat.scm.inventoryService.constant.UnitOfMeasure;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false) // Explicit usage per field
public class UnitOfMeasureConverter implements AttributeConverter<UnitOfMeasure, String> {

    @Override
    public String convertToDatabaseColumn(UnitOfMeasure attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public UnitOfMeasure convertToEntityAttribute(String dbData) {
        return dbData != null ? UnitOfMeasure.fromCode(dbData): null;
    }
}