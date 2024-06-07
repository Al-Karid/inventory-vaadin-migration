package com.arti.inventory.mission.backend.model;

public enum Status {
    PENDING("EN ATTENTE"), 
    APPROVED("APPROUVE"), 
    REJECTED("REJETE");

    private final String customValue;

    Status(String customValue) {
        this.customValue = customValue;
    }

    @Override
    public String toString() {
        return customValue;
    }
}
