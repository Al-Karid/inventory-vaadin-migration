package com.arti.inventory.auto.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehiculeType type;

    @NotNull
    private String plate;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    private String color;

    private String vehiculeYear;

    @NotNull
    private String driverName;

    private Long currentMileage;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
}
