package com.arti.inventory.device.backend.model;

public enum PrinterCategory {
    LASER(1), HP(2), CANON(3);

    private int value;

    PrinterCategory(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
