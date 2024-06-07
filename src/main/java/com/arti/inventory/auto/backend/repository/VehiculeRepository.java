package com.arti.inventory.auto.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arti.inventory.auto.backend.model.Vehicule;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long>{

}
