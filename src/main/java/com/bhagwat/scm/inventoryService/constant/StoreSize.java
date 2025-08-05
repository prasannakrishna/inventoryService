package com.bhagwat.scm.inventoryService.constant;

public enum StoreSize {
    SMALL(5000, 15000),
    MEDIUM(15001, 30000),
    LARGE(30001, 60000),
    EXTRA_LARGE(60001, Integer.MAX_VALUE);

    private final int minSqFt;
    private final int maxSqFt;

    StoreSize(int minSqFt, int maxSqFt) {
        this.minSqFt = minSqFt;
        this.maxSqFt = maxSqFt;
    }

    public int getMinSqFt() {
        return minSqFt;
    }

    public int getMaxSqFt() {
        return maxSqFt;
    }

    public static StoreSize fromArea(int areaSqFt) {
        for (StoreSize size : values()) {
            if (areaSqFt >= size.minSqFt && areaSqFt <= size.maxSqFt) {
                return size;
            }
        }
        throw new IllegalArgumentException("Area out of defined ranges: " + areaSqFt);
    }
}