package com.arti.inventory.mission.backend.model;

public enum Mobility {
    PERSONAL_CAR("Personnelle"), 
    COMPANY_CAR("Société"), 
    PUBLIC_TRANSPORT("Co-voiturage"),
    OTHER("Autre");

    private final String customValue;

    Mobility(String customValue) {
        this.customValue = customValue;
    }

    @Override
    public String toString() {
        return customValue;
    }
    
}
