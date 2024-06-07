package com.arti.inventory.device.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arti.inventory.device.backend.model.Printer;

public interface PrinterRepository extends JpaRepository<Printer, Long>{

}
