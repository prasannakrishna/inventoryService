package com.bhagwat.scm.inventoryService.constant;

public enum SkuCategory {
    EDIBLES("Food & Grocery Items"),
    SNACKS("Packaged snacks, chips, biscuits, dry fruits"),
    BEVERAGES("Juices, tea, coffee, soft drinks, etc."),
    HOME_FURNISHING("Bedsheets, curtains, cushions, etc."),
    LIFESTYLE("Apparel, footwear, watches, accessories"),
    HOME_MEDICINE("First-aid, OTC medicines, supplements"),
    SPIRITUAL("Puja items, incense, idols, feng shui, etc."),
    PERSONAL_CARE("Shampoo, soap, cosmetics, skincare"),
    CLEANING_SUPPLIES("Household cleaners, mops, detergents"),
    STATIONERY("Pens, notebooks, office supplies"),
    ELECTRONICS("Small gadgets, chargers, headphones"),
    PET_SUPPLIES("Pet food, toys, grooming items"),
    TOYS_AND_GAMES("Board games, dolls, educational toys"),
    HEALTH_AND_WELLNESS("Vitamins, fitness gear, yoga mats"),
    AUTOMOTIVE("Car accessories, lubricants, parts");

    private final String description;

    SkuCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
