package com.arti.inventory.device.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipv4;

    private String hostname;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "connexion_mode")
    @Enumerated(EnumType.STRING)
    private ConnexionMode connexionMode;

    @Column(name = "assigned_to")
    private String assignedTo;

    private String direction;

    private Boolean online;
}
