package com.example.java_core.p04_enum;

public enum Season2 {
    SPRING(0),
    SUMMER(10),
    FALL(20),
    WINTER(30);

    private int value;

    Season2(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
