package com.arti.inventory.device.backend.model;

import jakarta.persistence.Column;
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
public class Printer extends Device {

    private String label;
    
    private String ink1;
    private String ink2;
    private String ink3;
    private String ink4;

    private PrinterCategory category;

    @Column(name = "details_page")
    private String detailsPage;

    private transient PrinterDetail details;

}
