package com.bhagwat.scm.inventoryService.constant;

public enum CountFrequency {
    DAILY(1),
    WEEKLY(7),
    BIWEEKLY(14),
    MONTHLY(30),
    QUARTERLY(90),
    HALF_YEARLY(182),
    YEARLY(365);

    private final int approxDays;

    CountFrequency(int approxDays) {
        this.approxDays = approxDays;
    }

    public int getApproxDays() {
        return approxDays;
    }

    public static CountFrequency fromString(String value) {
        for (CountFrequency freq : values()) {
            if (freq.name().equalsIgnoreCase(value)) {
                return freq;
            }
        }
        throw new IllegalArgumentException("Unknown frequency: " + value);
    }
}