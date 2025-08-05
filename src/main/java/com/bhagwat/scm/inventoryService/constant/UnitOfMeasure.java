package com.bhagwat.scm.inventoryService.constant;

public enum UnitOfMeasure { // Weight Units
    KILOGRAM("KGM", "Kilogram", UnitType.WEIGHT),
    GRAM("GRM", "Gram", UnitType.WEIGHT),
    POUND("LBR", "Pound", UnitType.WEIGHT),
    OUNCE("ONZ", "Ounce", UnitType.WEIGHT),
    TONNE("TNE", "Metric Tonne", UnitType.WEIGHT),

    // Volume Units
    LITRE("LTR", "Litre", UnitType.VOLUME),
    MILLILITRE("MLT", "Millilitre", UnitType.VOLUME),
    GALLON("GAL", "US Gallon", UnitType.VOLUME),
    CUBIC_METER("MTQ", "Cubic Meter", UnitType.VOLUME),
    CUBIC_FEET("FTQ", "Cubic Feet", UnitType.VOLUME),

    // Length Units
    METER("MTR", "Meter", UnitType.LENGTH),
    CENTIMETER("CMT", "Centimeter", UnitType.LENGTH),
    MILLIMETER("MMT", "Millimeter", UnitType.LENGTH),
    INCH("INH", "Inch", UnitType.LENGTH),
    FOOT("FOT", "Foot", UnitType.LENGTH),
    YARD("YRD", "Yard", UnitType.LENGTH);

    private final String code;
    private final String description;
    private final UnitType type;

    UnitOfMeasure(String code, String description, UnitType type) {
        this.code = code;
        this.description = description;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public UnitType getType() {
        return type;
    }

    public enum UnitType {
        WEIGHT, VOLUME, LENGTH
    }
}