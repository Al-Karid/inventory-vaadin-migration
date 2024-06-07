package com.arti.inventory.device.backend.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Computer extends Device {

    private String label;
    
    private String category;

    private transient Integer ipToInteger;

}
