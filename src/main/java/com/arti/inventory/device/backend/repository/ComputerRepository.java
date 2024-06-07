package com.arti.inventory.device.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arti.inventory.device.backend.model.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    
}
