package com.arti.inventory.mission.backend.model;

public enum Transportation {
    CAR("Voiture"), 
    TRAIN("Train"), 
    PLANE("Avion"),
    BOAT("Bateau"), 
    OTHER("Autre");

    private final String customValue;

    Transportation(String customValue) {
        this.customValue = customValue;
    }

    @Override
    public String toString() {
        return customValue;
    }
}
