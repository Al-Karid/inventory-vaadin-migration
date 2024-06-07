package com.arti.inventory.mission.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arti.inventory.mission.backend.model.Perdiem;

@Repository
public interface PerdiemRepository extends JpaRepository<Perdiem, Long>{

    public Perdiem findByCode(String code);
}
