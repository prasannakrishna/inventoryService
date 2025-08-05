package com.bhagwat.scm.inventoryService.constant;

public enum SkuState {
    SOLID("Solid form, like soap, brick, or packaged snack"),
    LIQUID("Liquid form, like oil, sanitizer, or beverages"),
    SEMI_SOLID("Semi-solid, like toothpaste, gel, or ointment"),
    POWDER("Powder form, like spices or protein mix"),
    GAS("Gaseous form or aerosols, like deodorant sprays"),
    GRANULAR("Granular materials like salt, sugar, rice"),
    PASTE("Paste-like consistency, thicker than gel"),
    FROZEN("Frozen items, e.g., ice cream or frozen peas"),
    GEL("Gel-based, like aloe gel or medical gels");

    private final String description;

    SkuState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}