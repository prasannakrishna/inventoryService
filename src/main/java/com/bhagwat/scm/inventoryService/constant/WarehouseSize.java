package com.bhagwat.scm.inventoryService.constant;

public enum WarehouseSize {
    SMALL(20000, 50000),
    MEDIUM(50001, 100000),
    LARGE(100001, 250000),
    EXTRA_LARGE(250001, Integer.MAX_VALUE);

    private final int minSqFt;
    private final int maxSqFt;

    WarehouseSize(int minSqFt, int maxSqFt) {
        this.minSqFt = minSqFt;
        this.maxSqFt = maxSqFt;
    }

    public int getMinSqFt() {
        return minSqFt;
    }

    public int getMaxSqFt() {
        return maxSqFt;
    }

    public static WarehouseSize fromArea(int areaSqFt) {
        for (WarehouseSize size : values()) {
            if (areaSqFt >= size.minSqFt && areaSqFt <= size.maxSqFt) {
                return size;
            }
        }
        throw new IllegalArgumentException("Area out of defined ranges: " + areaSqFt);
    }
}