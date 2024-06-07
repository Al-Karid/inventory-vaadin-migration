package com.arti.inventory.device.backend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ip;

    private String brand;

    @Column(name = "connexion_mode")
    @Enumerated(EnumType.STRING)
    private ConnexionMode connexionMode;

    private String serie;

    private String direction;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    private Boolean online;
}
