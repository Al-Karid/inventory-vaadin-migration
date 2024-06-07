package com.arti.inventory.mission.backend.model;


public enum MissionType {
    INLAND("Côte d'Ivoire"),
    AFRICA("Afrique"),
    ABOARD("Hors Afrique");

    private final String customValue;

    MissionType(String customValue) {
        this.customValue = customValue;
    }

    @Override
    public String toString() {
        return customValue;
    }
}
